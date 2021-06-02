package zhangdi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("jdbcUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 为框架提供UserDetailsService对象
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("=================configure AuthenticationManagerBuilder =========");

//        super.configure(auth);
        //指定UserDetailsService对象、
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());//加密方法
    }

    /**
     * 登陆页面管理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=================configure HttpSecurity =========");
        http.authorizeRequests()
                .antMatchers("/index","/mylogin","/login").permitAll()//设置访问的白名单
                .antMatchers("login/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mylogin")//自定义登陆界面
                .loginProcessingUrl("/login")//登陆表单提交界面
                .failureForwardUrl("/loginfailure")//登陆错误提示页面
                .and()
                .csrf().disable();//跨域

    }
}
