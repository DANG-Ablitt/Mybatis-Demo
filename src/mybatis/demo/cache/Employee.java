package mybatis.demo.cache;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id 主键 自增
     */
    private Integer id;
    /**
     * 用户名 对应数据库中的last_name
     */
    private String lastName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别  女：0 男：1
     */
    private String gender;

    /**
     * 无参构造器
     */
    public Employee() {
    }

    /**
     * 有参构造器
     * @param id
     * @param lastName
     * @param email
     * @param gender
     */
    public Employee(Integer id, String lastName, String email, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    /**
     * getter和setter方法
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 便于打印输出
     * @return Employee实例获取的值
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
