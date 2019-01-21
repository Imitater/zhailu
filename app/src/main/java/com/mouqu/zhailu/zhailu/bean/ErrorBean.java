package com.mouqu.zhailu.zhailu.bean;

public class ErrorBean {
    private int status;
    private String msg;
    private String code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}