package mybatis.demo.resultMap;

public interface DepartmentMapDao {
    /**
     * 查询方法
     */
    public Department getDepById(Integer id);
    /**
     * 查询方法  部门中的员工
     */
    public Department getDepByEmpId(Integer id);
}
