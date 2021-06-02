package zhangdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import zhangdi.dao.DepartmentImpl;
import zhangdi.dao.EmployeeImpl;
import zhangdi.dao.EmployeeInformationImpl;
import zhangdi.dao.PermissionImpl;
import zhangdi.entity.Department;
import zhangdi.entity.Employee;
import zhangdi.entity.EmployeeInformation;
import zhangdi.entity.Permission;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class Application {

    @Autowired
    private DepartmentImpl departmentimpl;
    @Autowired
    private EmployeeImpl employeeimpl;
    @Autowired
    private EmployeeInformationImpl employeeInformationimpl;
    @Autowired
    private PermissionImpl permissionimpl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//   @PostConstruct
    public void jdbcInit(){

       /**
        * 添加权限
        */
       //管理员权限
       //创建管理员权限
       Permission permission = new Permission();
       permission.setPermission("ROLE_ADMIN");


       /**
        * 添加部门信息
        */
       Department department = new Department();
       department.setName("管理部门");
       department.setAddress("433");

       /**
        * 设置部门权限并保存
        */
       Set<Permission> permissions = new HashSet<Permission>();
       permissions.add(permission);
       Set<Department> departments = new HashSet<Department>();
       departments.add(department);
       permission.setDepartment(departments);
       department.setPermission(permissions);

       permissionimpl.save(permission);

       /**
        * 添加个人信息
        */
       EmployeeInformation employeeInformation = new EmployeeInformation();
       employeeInformation.setName("管理员");

       /**
        * 添加用户
        */
       Employee employee = new Employee();
        //用户名
        employee.setUsername("admin");
        //密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode("admin"));
        //四个true
        employee.setIscredentials(true);
        employee.setIsenable(true);
        employee.setIslock(true);
        employee.setIsexpired(true);

        //两个时间
        employee.setCreatetime(new Date());
        employee.setLogintime(new Date());

       /**
        * 保存用户个人详细信息
        */
       employee.setEmployeeinformation(employeeInformation);
       employeeInformation.setEmployee(employee);
       employeeInformationimpl.save(employeeInformation);

       /**
        * 保存用户
        */

       employee.setDepartment(departments);
       Set<Employee> employees = new HashSet<Employee>();
       department.setEmployee(employees);

        departmentimpl.save(department);
       employeeimpl.save(employee);

    }
}
