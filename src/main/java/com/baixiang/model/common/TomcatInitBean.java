package com.baixiang.model.common;

import org.springframework.stereotype.Component;

//@Component
public class TomcatInitBean {
    private int port;
    private String contextPath;

    public TomcatInitBean(int port, String contextPath) {
        this.port = port;
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
