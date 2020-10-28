package mybatis.demo.cache;


public interface EmployeeCache {
    /**
     * 查询方法
     */
    public Employee getEmpById(Integer id);
    /**
     * 修改方法
     */
    public void updateEmp(Employee employee);
}
