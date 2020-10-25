package mybatis.demo.resultMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

public class ResultMapMain {

    /**
     * 生成SqlSessionFactory实例的方法
     */
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        /**
         * 从 XML 中构建 SqlSessionFactory
         */
        String resource = "mybatis/demo/resultMap/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     * 查询数据
     */
    @Test
    public void EmpById() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeMapDao mapper = session.getMapper(EmployeeMapDao.class);
            Employee employee = mapper.getEmpById(3);
            System.out.print(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     */
    @Test
    public void EmpByEmpANDdepId() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeMapDao mapper = session.getMapper(EmployeeMapDao.class);
            Employee employee = mapper.getEmpANDDepById(2);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
