package mybatis.demo.cache;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

public class EmployeeCacheMain {

    /**
     * 生成SqlSessionFactory实例的方法
     */
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        /**
         * 从 XML 中构建 SqlSessionFactory
         */
        String resource = "mybatis/demo/cache/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     * 两级缓存：
     * 一级缓存：（本地缓存）SqlSession级别的缓存，一级缓存是一直开启的
     * SqlSession级别的一个Map
     * 与数据库同一次会话期间查询到的数据会放在本地缓存中
     * 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去访问数据库
     * 一级缓存失效的情况：（需要再次向数据库查询）
     * 1.SqlSession不同
     * 2.SqlSession相同，查询条件不同（当前一级缓存中还没有这个数据）
     * 3.SqlSession相同，两次查询中间执行了增删改操作（这次增删改可能对当前数据有影响）
     * 4.SqlSession相同，手动清除了一级缓存（缓存清空）
     */
    //一级缓存演示：
    @Test
    public void testFirstLevelCache() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            EmployeeCache mapper = session.getMapper(EmployeeCache.class);
            /**
             * 通过下边4行语句可以发现：SqlSession相同的情况下，
             * 只从数据库中查询了一次，下一次的值从缓存中获取
             */
            //Employee employee = mapper.getEmpById(3);
            //System.out.println(employee);
            //Employee employees = mapper.getEmpById(3);
            //System.out.println(employees);
            //System.out.println(employee==employees);
            /**
             * SqlSession不同的情况：从数据库查询了两次数据
             */
            //Employee employee = mapper.getEmpById(1);
            //System.out.println(employee);
            //再创建一个SqlSession
            //SqlSession session1 = getSqlSessionFactory().openSession();
            //EmployeeCache mapper1 = session1.getMapper(EmployeeCache.class);
            //Employee employees = mapper1.getEmpById(1);
            //System.out.println(employees);
            //手动关闭SqlSession2
            //session1.close();
            /**
             * SqlSession相同，查询条件不同
             * 从数据库中查询了两次（当前一级缓存中还没有id为1的数据）
             */
            //Employee employee = mapper.getEmpById(3);
            //System.out.println(employee);
            //Employee employees = mapper.getEmpById(1);
            //System.out.println(employees);
            /**
             * SqlSession相同，两次查询中间执行了增删改操作
             */
            //Employee employee = mapper.getEmpById(3);
            //System.out.println(employee);
            //穿插一条修改语句
            //Employee employee_update = new Employee(13, "Mut", "mut@google.com", "0");
            //mapper.updateEmp(employee_update);
            //手动提交数据(否则数据不会提交)
            //session.commit();
            //Employee employees = mapper.getEmpById(3);
            //System.out.println(employees);
            /**
             * SqlSession相同，手动清除了一级缓存
             */
            //Employee employee = mapper.getEmpById(3);
            //System.out.println(employee);
            //清空一级缓存
            //session.clearCache();
            //Employee employees = mapper.getEmpById(3);
            //System.out.println(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 二级缓存说明：（全局缓存）基于namespace级别的缓存，一个namespace对应一个二级缓存
     * 工作机制：
     * 1.一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中
     * 2.如果会话关闭，一级缓存中的数据就会被保存在二级缓存中，新的会话信息需要可以到二级缓存中去找
     * 3.不同namespace查出的数据会放在自己对应的缓存中（Map）
     * 使用：
     * 1）开启二级缓存配置
     * 2）Mapper.xml中配置二级缓存
     * 3）POJO要实现实例化接口
     */
    //二级缓存演示：

    /**
     * 踩坑：
     *注意：二级缓存存在于 SqlSessionFactory 生命周期中
     *所以：前提是操作必须在同一个SqlSessionFactory 中进行
     *如果实例化了多个不同的 SqlSessionFactory 对象，这样在后续的SQL操作中，是不可能命中二级缓存的。
     *参考：https://blog.csdn.net/xingzhe123456789000/article/details/105021334
     */
    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session1 = factory.openSession();
        SqlSession session2 = factory.openSession();
        try{
            //第一次从数据库中取值
            EmployeeCache mapper1 = session1.getMapper(EmployeeCache.class);
            Employee employee1 = mapper1.getEmpById(3);
            System.out.println(employee1);
            session1.close();
            //第二次从缓存中取值
            EmployeeCache mapper2 = session2.getMapper(EmployeeCache.class);
            Employee employee2 = mapper2.getEmpById(3);
            System.out.println(employee2);
            session2.close();
        }finally { }
    }

    /**
     * 和缓存有关的设置和属性
     * （1）cacheEnabled=true false：关闭二级缓存（不影响一级缓存）
     * （2）每个select标签都有useCache=true  false：关闭二级缓存（不影响一级缓存）
     * （3）每个增删改标签都有flushCache=true 一级和二级缓存都会清理
     * （4）clearCache()只清除当前session的一级缓存
     * （5）localCacheScope：本地缓存作用域
     */

    /**
     * 我们可以通过实现Cache接口来自定义二级缓存
     * 第三方缓存方案整合：
     * （1）导入第三方缓存包
     * （2）导入与第三方缓存适配的整合包（官方GitHub上有）
     * （3）mapper.xml中使用自定义缓存：
     *        <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
     */
}

