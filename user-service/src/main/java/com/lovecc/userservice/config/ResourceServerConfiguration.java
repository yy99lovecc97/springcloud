package com.lovecc.userservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;
    Logger logger = LoggerFactory.getLogger(ResourceServerConfiguration.class);
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        logger.info("Configuring ResourceServerSecurityConfigurer ");
        resources.resourceId("user-service").tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .regexMatchers(".*swagger.*",".*v2.*",".*webjars.*","/user/login.*","/user/registry.*","/user/test.*",".*actuator.*").permitAll()
                .antMatchers("/**").authenticated();
    }
}
