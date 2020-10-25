package mybatis.demo.crud;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeCrudMain {

    /**
     * 生成SqlSessionFactory实例的方法
     */
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        /**
         * 从 XML 中构建 SqlSessionFactory
         */
        String resource = "mybatis/demo/crud/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     * 下面通过单元测试来测试一下CRUD
     * 查询数据
     */
    @Test
    public void EmpById() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee = mapper.getEmpById(3);
            System.out.print(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void EmpInsert() {
        //注意：获取到的SqlSession不会自动提交数据
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee_insert = new Employee(null, "Mbb", "mbb@google.com", "0");
            mapper.addEmp(employee_insert);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     */
    @Test
    public void EmpUpdate() {
        //注意：获取到的SqlSession不会自动提交数据
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee_update = new Employee(13, "Mut", "mut@google.com", "0");
            mapper.updateEmp(employee_update);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     */
    @Test
    public void EmpDelete() {
        //注意：获取到的SqlSession不会自动提交数据
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            mapper.deleteEmpById(13);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/**
 * 关于手动提交数据的说明：
 * （1）SqlSessionFactory.openSession();需要手动提交
 * （2）SqlSessionFactory.openSession(true);自动提交
 */

    /**
     * 添加数据 验证主键策略
     */
    @Test
    public void EmpInsertKeys() {
        //注意：获取到的SqlSession不会自动提交数据
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee_insert = new Employee(null, "Mbb", "mbb@google.com", "0");
            mapper.addEmpKeys(employee_insert);
            //手动提交数据(否则数据不会提交)
            session.commit();
            //打印返回的 id 值
            System.out.print("id:"+employee_insert.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 下面验证MyBatis参数处理
     * 注意：单个参数和多个参数中POJO作为参数的情况上边已经测试过
     * 下面验证多个参数的形式
     * 命名参数（去掉@param注解可以体验普通方式）
     */
    @Test
    public void EmpByAndId() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee = mapper.getEmpAndById(1, "0");
            System.out.print(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
         *Map作为参数
         */
        @Test
        public void EmpByMapId() {
            try (SqlSession session = getSqlSessionFactory().openSession()) {
                EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
                //封装一个Map
                Map<String,Object> map=new HashMap<>();
                //注意：这里将id封装成了ID，gender封装成了Gender
                map.put("ID",1);
                map.put("Gender","0");
                Employee employee = mapper.getEmpMapById(map);
                System.out.print(employee);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     *查询多条记录，返回一个集合
     */
    @Test
    public void EmpsByLastNameLike() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            List<Employee> like = mapper.getEmpsByLastNameLike("%n%");
            //遍历集合打印输出
            for(Employee employee:like){
                System.out.println(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *返回一条记录的Map,Key是列名，值是对应的值
     */
    @Test
    public void EmpsByIdReturnMap() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Map<String,Object> map= mapper.getEmpByIdReturnMap(1);
            //打印出对应的用户名
            System.out.println(map.get("last_name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *多条记录封装一个Map,Map<Integer,Employee>
     *键是这条记录的主键，值是封装后的JavaBean
     */
    @Test
    public void EmpsByLastNameLikeReturnMap() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Map<Integer,Employee> map= mapper.getEmpByLastNameLikeReturnMap("%n%");
            //遍历Map打印输出
            for(Integer id:map.keySet()){
                System.out.println(map.get(id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
