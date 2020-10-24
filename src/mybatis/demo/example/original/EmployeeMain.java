package mybatis.demo.example.original;

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
        String resource = "mybatis/demo/example/original/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /**
         * 通过 SqlSession 实例来直接执行已映射的 SQL 语句（旧方法）
         *从 SqlSessionFactory 中获取 SqlSession
         */
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Employee employee = (Employee) session.selectOne("com.mybatis.demo.original.selectEmp", 1);
            //输出从数据库中获取到的值
            System.out.print(employee);
        }
        /**
         * 第一次查询到的结果：（可以发现lastName没有获取到值，因为数据库中对应的是last_name）
         * Employee{id=1, lastName='null', email='tom@google.com', gender='0'}
         * 解决方案：
         */

    }
}
