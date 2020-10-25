package mybatis.demo.resultMap;

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


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentname='" + departmentname + '\'' +
                '}';
    }

}
