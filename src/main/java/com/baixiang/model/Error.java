package com.baixiang.model;

/**
 * Created by shenjj on 2017/4/12.
 */
public class Error {
    String errorMsg;
    String errorType;

    public Error(String errorType,String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorType = errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorId='" + errorType + '\'' +
                '}';
    }
}
