package com.baixiang.config;

import com.baixiang.model.TomcatInitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ServerConfig implements EmbeddedServletContainerCustomizer {

//    @Autowired
//    TomcatInitBean tomcatInitBean;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setPort(tomcatInitBean.getPort());
//        configurableEmbeddedServletContainer.setContextPath(tomcatInitBean.getContextPath());
    }
}
