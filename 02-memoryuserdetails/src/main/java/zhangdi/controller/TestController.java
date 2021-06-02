package zhangdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import zhangdi.dao.EmployeeImpl;
import zhangdi.entity.Department;
import zhangdi.entity.Employee;
import zhangdi.entity.Permission;

import java.util.Collection;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private EmployeeImpl employeeimpl;

    @RequestMapping("/test")
    private String test(){
        Employee employee = employeeimpl.findByUsername("admin");

        List<Department> departments = (List<Department>) employee.getDepartment();
        Department department = departments.get(0);

        List<Permission> permissions = (List<Permission>) department.getPermission();
        Permission permission = permissions.get(0);

        return department.toString()+permission.toString();

    }

    @RequestMapping("/mylogin")
    private ModelAndView mylogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mylogin");

        return mv;

    }

}
