package mybatis.demo.dynamicsql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmployeeDynamicSqlMain {

    /**
     * 生成SqlSessionFactory实例的方法
     */
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        /**
         * 从 XML 中构建 SqlSessionFactory
         */
        String resource = "mybatis/demo/dynamicsql/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     *查询员工：
     *要求携带了哪个字段查询条件就带上这个字段值
     */
    @Test
    public void EmpsByConditionIf() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeDynamicSqlDao mapper = session.getMapper(EmployeeDynamicSqlDao.class);
            List<Employee> employee=mapper.getEmpsByConditionIf(new Employee(1,null,null,null));
            for(Employee emps:employee){
                System.out.print(emps);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    public void EmpsByConditionTrim() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeDynamicSqlDao mapper = session.getMapper(EmployeeDynamicSqlDao.class);
            List<Employee> employee=mapper.getEmpsByConditionTrim(new Employee(null,"%o%",null,null));
            for(Employee emps:employee){
                System.out.print(emps);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    @Test
    public void EmpsByConditionChoose() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeDynamicSqlDao mapper = session.getMapper(EmployeeDynamicSqlDao.class);
            List<Employee> employee=mapper.getEmpsByConditionChoose(new Employee(null,"%u%",null,null));
            for(Employee emps:employee){
                System.out.print(emps);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * 修改数据
     */
    @Test
    public void updateEmp() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeDynamicSqlDao mapper = session.getMapper(EmployeeDynamicSqlDao.class);
            mapper.updateEmp(new Employee(1,null,"w@google.com",null));
            //手动提交数据
            session.commit();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
