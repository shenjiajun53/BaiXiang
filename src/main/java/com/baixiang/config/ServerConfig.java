package com.baixiang.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

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
