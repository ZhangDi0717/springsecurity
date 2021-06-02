package zhangdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zhangdi.comment.MyFailureHandler;
import zhangdi.comment.MySuccessHandler;
import zhangdi.dao.EmployeeImpl;
import zhangdi.entity.Department;
import zhangdi.entity.Employee;
import zhangdi.entity.MyUserDetails;
import zhangdi.entity.Permission;
import zhangdi.vo.LoginResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * 登陆用户信息封装类
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {


    @Autowired
    private MySuccessHandler successHandler = new MySuccessHandler();

    @Autowired
    private MyFailureHandler failureHandler = new MyFailureHandler();
    @Autowired
    private EmployeeImpl employeeimpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.获取用户信息
        Employee employee = employeeimpl.findByUsername(username);
        if (employee !=null){
            //获取权限
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();//权限列表
            GrantedAuthority authority = null;//权限
            Set<Department> departmentSet = employee.getDepartment();//获取用户所处部门
            Iterator<Department> departmentIterator = departmentSet.iterator();//
            while(departmentIterator.hasNext()) {//轮询用户所属部门
                Department department = departmentIterator.next();
                Set<Permission> permissionSet = department.getPermission();//获取权限信息
                Iterator<Permission> permissionIterator = permissionSet.iterator();
                while (permissionIterator.hasNext()){//轮询权限
                    Permission permission = permissionIterator.next();
                    authority = new SimpleGrantedAuthority(permission.getPermission());//封装权限
                    authorities.add(authority);//添加进入权限列表
                }
            }


            //封装权限和应答路径
            urlAndauthority(authorities);


            //封装MyUserDetails
            MyUserDetails myUserDetails = new MyUserDetails(employee.getUsername(),
                    employee.getPassword(),
                    employee.getIsexpired(),
                    employee.getIslock(),
                    employee.getIscredentials(),
                    employee.getIsenable(),
                    authorities);

            return myUserDetails;
        }else {
            //用户名把密码不正确
            LoginResult loginResult = new LoginResult();
            loginResult.setCode(203);
            loginResult.setMsg("用户名和密码不正确");
            failureHandler.setLoginResult(loginResult);
        }

        System.out.println("=======执行错误============");
        return null;
    }


    private void urlAndauthority(List<GrantedAuthority> authorities){
        for (GrantedAuthority grantedAuthority:authorities) {
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                LoginResult loginResult = new LoginResult();
                loginResult.setCode(200);
                loginResult.setMsg("/admin/homepage");
                successHandler.setLoginResult(loginResult);
            }
        }
    }

}
