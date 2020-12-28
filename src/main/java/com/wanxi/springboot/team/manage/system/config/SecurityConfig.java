package com.wanxi.springboot.team.manage.system.config;

import com.wanxi.springboot.team.manage.system.filter.JwtAuthenticationTokenFilter;
import com.wanxi.springboot.team.manage.system.handler.RestfulAccessDeniedHandler;
import com.wanxi.springboot.team.manage.system.point.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement()// 基于token，所以不需要 securityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()//"/layui/**","/css/**", "/js/**", "/fonts/**","/user/login","/user/register","/user/getUserByName","/login.html","/register.html", "/rabbit/**"
                .antMatchers("/layui/**","/css/**", "/js/**", "/fonts/**","/user/login","/user/register","/user/getUserByName","/login.html","/register.html", "/rabbit/**").permitAll() //都可以访问
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated() // 任何请求都需要认证
//                .and()
//                .formLogin()
//                .loginPage(frontPort+"login.html")
//                .permitAll()
                .and()
                .userDetailsService(userDetailsService);
         //自定义 登录界面
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
        //添加自定义未授权和未登录结果返回
//        http.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

//    //   这是将用户存入内存的第一种方式
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    //   这是将用户存入内存的第二种方式
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(new User("jeffery", passwordEncoder().encode("123456"), AuthorityUtils.createAuthorityList("wx:product:read", "wx:product:delete")));
        return manager;
    }

    //   这是密码加密解密器 可以用于加密密码也可以用户对比原始密码与加密密码，你只需暴露，security会将你暴露的passwordEncoder 作为默认的
    //   passwordEncoder.encode()  用于加密密码
    //   passwordEncoder.matchs(原始密码，加密密码)  对比原始密码与加密密码
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}