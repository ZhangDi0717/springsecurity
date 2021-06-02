package zhangdi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isexpired;

    @Column(nullable = false)
    private Boolean islock;

    @Column(nullable = false)
    private Boolean iscredentials;

    @Column(nullable = false)
    private Boolean isenable;


    @Column(nullable = false)
    private Date createtime;

    @Column(nullable = false)
    private Date logintime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsenable() {
        return isenable;
    }

    public Boolean getIsexpired() {
        return isexpired;
    }

    public void setIsexpired(Boolean isexpired) {
        this.isexpired = isexpired;
    }


    public void setIsenable(Boolean isenable) {
        this.isenable = isenable;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Boolean getIscredentials() {
        return iscredentials;
    }

    public void setIscredentials(Boolean iscredentials) {
        this.iscredentials = iscredentials;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", optional = false)
    private EmployeeInformation employeeinformation;

    public EmployeeInformation getEmployeeinformation() {
        return employeeinformation;
    }

    public void setEmployeeinformation(EmployeeInformation employeeinformation) {
        this.employeeinformation = employeeinformation;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Department> department;

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }
}
