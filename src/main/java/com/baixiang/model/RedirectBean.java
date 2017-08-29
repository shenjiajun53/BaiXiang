package com.baixiang.model;

/**
 * Created by shenjj on 2017/4/12.
 */
public class RedirectBean {
    String redirect;
    int status;

    public RedirectBean(String redirect) {
        this.redirect = redirect;
    }

    public RedirectBean(int status, String redirect) {
        this.status=status;
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
