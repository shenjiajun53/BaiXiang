package com.baixiang.model.response;

/**
 * Created by shenjj on 2017/4/14.
 */
public class BaseBean {
    int status;

    public BaseBean(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
