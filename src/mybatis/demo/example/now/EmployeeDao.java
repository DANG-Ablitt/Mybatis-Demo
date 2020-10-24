package mybatis.demo.example.now;

public interface EmployeeDao {
    /**
     * 该方法用于根据id从数据库中查询数据
     */
    public Employee getEmpById(Integer id);
}
