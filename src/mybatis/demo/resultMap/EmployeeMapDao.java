package mybatis.demo.resultMap;

public interface EmployeeMapDao {
    /**
     * 查询方法
     */
    public Employee getEmpById(Integer id);
    /**
     * 查询方法
     *（1）联合查询：级联属性封装结果集（对应empANDdep1）
     *（2）通过association标签封装（对应empANDdep2）
     *（3）通过association分步查询（对应empANDdep3）
     */
    public Employee getEmpANDDepById(Integer id);
}
