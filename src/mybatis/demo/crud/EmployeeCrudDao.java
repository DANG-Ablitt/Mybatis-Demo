package mybatis.demo.crud;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//注意：MyBatis框架允许增删改直接定义以下类型返回值
//     Integer  Long  Boolean  void
public interface EmployeeCrudDao {
    /**
     * 查询方法
     */
    public Employee getEmpById(Integer id);
    /**
     * 查询方法 验证传递多个参数
     * 注意：这里通过@Param注解启用了命名参数
     */
    public Employee getEmpAndById(@Param("id") Integer id, @Param("gender")String gender);
    /**
     * 查询方法 验证通过Map多个参数
     */
    public Employee getEmpMapById( Map<String,Object> map );
    /**
     * 查询方法 查询多条记录，返回一个集合
     */
    public List<Employee> getEmpsByLastNameLike(String lastName);
    /**
     * 查询方法 返回一条记录的Map,Key是列名，值是对应的值
     */
    public Map<String,Object> getEmpByIdReturnMap(Integer id);
    /**
     * 查询方法 多条记录封装一个Map,Map<Integer,Employee>
     * 键是这条记录的主键，值是封装后的JavaBean
     * 注意：需要通过@MapKey注解告诉MyBatis封装Map的时候使用哪个属性作为主键
     */
    @MapKey("id")
    public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
    /**
     * 添加方法
     */
    public void addEmp(Employee employee);
    /**
     * 添加方法 验证主键策略
     */
    public void addEmpKeys(Employee employee);
    /**
     * 修改方法
     */
    public void updateEmp(Employee employee);
    /**
     * 删除方法
     */
    public void deleteEmpById(Integer id);
}
