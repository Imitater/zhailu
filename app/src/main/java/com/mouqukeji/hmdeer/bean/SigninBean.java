package com.mouqukeji.hmdeer.bean;

import java.util.List;

public class SigninBean {
    private String user_id;
    private String token;
    private List<?> user;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<?> getUser() {
        return user;
    }

    public void setUser(List<?> user) {
        this.user = user;
    }
}
