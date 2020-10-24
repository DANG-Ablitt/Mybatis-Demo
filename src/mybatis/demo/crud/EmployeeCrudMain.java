package mybatis.demo.crud;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

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
     *下面通过单元测试来测试一下CRUD
     * 查询数据
     */
    @Test
    public void EmpById(){
        try (SqlSession session = getSqlSessionFactory().openSession()) {
        EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
        Employee employee =mapper.getEmpById(1);
        System.out.print(employee);
        } catch (IOException e) { e.printStackTrace(); }
    }
    /**
     * 添加数据
     */
    @Test
    public void EmpInsert(){
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee_insert = new Employee(null, "Mbb", "mbb@google.com", "0");
            mapper.addEmp(employee_insert);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) { e.printStackTrace(); }
    }
    /**
     * 修改数据
     */
    @Test
    public void EmpUpdate(){
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            Employee employee_update = new Employee(13, "Mut", "mut@google.com", "0");
            mapper.updateEmp(employee_update);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) { e.printStackTrace(); }
    }
    /**
     * 删除数据
     */
    @Test
    public void EmpDelete(){
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCrudDao mapper = session.getMapper(EmployeeCrudDao.class);
            mapper.deleteEmpById(13);
            //手动提交数据(否则数据不会提交)
            session.commit();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
