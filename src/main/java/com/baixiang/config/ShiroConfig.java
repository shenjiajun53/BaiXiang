package com.baixiang.config;


import com.baixiang.security.JpaRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenjj on 2017/4/19.
 */

@Configuration
public class ShiroConfig {

    @Bean(name = "characterEncodingFilter")
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addInitParameter("encoding", "UTF-8");
        bean.addInitParameter("forceEncoding", "true");
        bean.setFilter(new CharacterEncodingFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/manage/sign_in");
        shiroFilter.setSuccessUrl("/manage");
        shiroFilter.setUnauthorizedUrl("/manage/sign_in");
        Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();


        filterChainDefinitionMapping.put("/index", "authc,roles[guest]");
        filterChainDefinitionMapping.put("/admin", "authc,roles[BangZhu]");
        filterChainDefinitionMapping.put("/manage", "authc,roles[BangZhu]");
        filterChainDefinitionMapping.put("/manage/edit_movie", "authc,roles[BangZhu]");
        filterChainDefinitionMapping.put("/api/edit_movie", "authc,roles[BangZhu]");
        filterChainDefinitionMapping.put("/manage/sign_in", "anon");
        filterChainDefinitionMapping.put("/manage/sign_up", "anon");
        filterChainDefinitionMapping.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager());

        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new FormAuthenticationFilter());
        filters.put("logout", new LogoutFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("user", new UserFilter());
        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public JpaRealm realm() {
        JpaRealm jpaRealm = new JpaRealm();
        return jpaRealm;
    }

//    @Bean(name = "realm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public PropertiesRealm realm() {
//        PropertiesRealm propertiesRealm = new PropertiesRealm();
//        propertiesRealm.init();
//        return propertiesRealm;
//    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
