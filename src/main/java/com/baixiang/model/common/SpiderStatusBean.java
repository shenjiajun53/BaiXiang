package com.baixiang.model.common;

public class SpiderStatusBean {
    private boolean isBtRunning = false;
    private boolean isHuaRunning = false;
    private boolean isDoubanRunnin = false;

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

    public boolean isDoubanRunnin() {
        return isDoubanRunnin;
    }

    public void setDoubanRunnin(boolean doubanRunnin) {
        isDoubanRunnin = doubanRunnin;
    }
}
