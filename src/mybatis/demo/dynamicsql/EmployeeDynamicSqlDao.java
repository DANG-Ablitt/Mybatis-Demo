package mybatis.demo.dynamicsql;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSqlDao {
    /**
     *查询员工：
     *要求携带了哪个字段查询条件就带上这个字段值
     */
    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    /**
     * 修改数据
     */
    public void updateEmp(Employee employee);
    /**
     * 对集合的处理
     */
    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Employee> employees);
}
