package mybatis.demo.crud;

public interface EmployeeCrudDao {
    /**
     * 查询方法
     */
    public Employee getEmpById(Integer id);
    /**
     * 添加方法
     */
    public void addEmp(Employee employee);
    /**
     * 修改方法
     */
    public void updateEmp(Employee employee);
    /**
     * 删除方法
     */
    public void deleteEmpById(Integer id);
}
