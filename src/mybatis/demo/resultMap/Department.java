package mybatis.demo.resultMap;

import org.apache.ibatis.type.Alias;

import java.util.List;

public class Department {

    /**
     * 部门ID 主键 自增
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String departmentname;
    /**
     *部门中的员工
     */
    private List<Employee> employee;

    /**
     *  getter和setter
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentname='" + departmentname + '\'' +
                '}';
    }

}
