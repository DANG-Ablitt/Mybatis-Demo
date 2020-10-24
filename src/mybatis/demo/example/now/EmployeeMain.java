package mybatis.demo.example.now;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class EmployeeMain {

    public static void main(String[] args) throws IOException {
        /**
         * 从 XML 中构建 SqlSessionFactory
         */
        String resource = "mybatis/demo/example/now/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /**
         * 使用和指定语句的参数和返回值相匹配的接口(新方法)
         *从 SqlSessionFactory 中获取 SqlSession
         */
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeDao mapper  = session.getMapper(EmployeeDao.class);
            Employee employee = mapper.getEmpById(1);
            //打印结果
            System.out.print(employee);
        }



    }
}
