package com.baixiang.listener;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by shenjj on 2017/5/17.
 */
@Component
public class SpringEventListener {
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();

//        ((DefaultWebSecurityManager) context.getBean("securityManager")).setRealm(
//                (Realm) context.getBean("hibernateRealm"));
    }
}
