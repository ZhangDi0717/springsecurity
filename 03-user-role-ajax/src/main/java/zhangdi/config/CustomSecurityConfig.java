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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zhangdi.filter.VerificationCodeFilter;


/**
 * spring security 安全框架配置类
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("jdbcUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;
    /**
     * 为框架提供UserDetailsService对象
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("=================configure AuthenticationManagerBuilder =========");

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
                .antMatchers("/index","/login","/css/**","/images/**","/js/**","/layui-v2.6.5/**","/captcha/**").permitAll()//设置访问的白名单
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/apply/**").hasRole("APPLY")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage("/index")//自定义登陆界面
                .loginProcessingUrl("/login")//登陆表单提交界面
                .and()
                .csrf().disable();//跨域

        //在过滤器链条中添加自定义的过滤器
        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
