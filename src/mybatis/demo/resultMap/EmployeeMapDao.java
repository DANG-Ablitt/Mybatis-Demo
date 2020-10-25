package mybatis.demo.resultMap;

public interface EmployeeMapDao {
    /**
     * 查询方法
     */
    public Employee getEmpById(Integer id);
    /**
     * 查询方法
     */
    public Employee getEmpANDDepById(Integer id);

}
