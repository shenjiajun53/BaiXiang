package com.baixiang.model;

public class SpiderStatusBean {
    private boolean isBtRunning = false;
    private boolean isHuaRunning = false;

    public boolean isBtRunning() {
        return isBtRunning;
    }

    public void setBtRunning(boolean btRunning) {
        isBtRunning = btRunning;
    }

    public boolean isHuaRunning() {
        return isHuaRunning;
    }

    public void setHuaRunning(boolean huaRunning) {
        isHuaRunning = huaRunning;
    }
}
