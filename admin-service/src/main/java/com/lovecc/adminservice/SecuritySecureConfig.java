package com.lovecc.adminservice;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //进行授权成功后处理。授权成功后，重定向回之前访问的页面
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        //redirectTo：关闭当前页，跳转到指定页； navigateTo：保留当前页，跳转到指定页；
        handler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath+"/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath+"/login").successHandler(handler).and()
                .logout().logoutUrl(adminContextPath+"/logout").and()
                .httpBasic().and()
                .csrf().disable();
    }
}
