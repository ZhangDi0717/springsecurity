package zhangdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zhangdi.dao.EmployeeImpl;
import zhangdi.entity.Department;
import zhangdi.entity.Employee;
import zhangdi.entity.MyUserDetails;
import zhangdi.entity.Permission;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class JdbcUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeImpl employeeimpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.获取用户信息
        Employee employee = employeeimpl.findByUsername(username);
        if (employee !=null){
            //获取权限
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority authority = null;
            Set<Department> departmentSet = employee.getDepartment();
            Iterator<Department> departmentIterator = departmentSet.iterator();
            while(departmentIterator.hasNext()) {
                Department department = departmentIterator.next();
                Set<Permission> permissionSet = department.getPermission();
                Iterator<Permission> permissionIterator = permissionSet.iterator();
                while (permissionIterator.hasNext()){
                    Permission permission = permissionIterator.next();
                    authority = new SimpleGrantedAuthority(permission.getPermission());
                    authorities.add(authority);
                }
            }


            //封装MyUserDetails
            MyUserDetails myUserDetails = new MyUserDetails(employee.getUsername(),
                    employee.getPassword(),
                    employee.getIsexpired(),
                    employee.getIslock(),
                    employee.getIscredentials(),
                    employee.getIsenable(),
                    authorities);

            return myUserDetails;
        }

        System.out.println("=======执行错误============");
        return null;
    }
}
