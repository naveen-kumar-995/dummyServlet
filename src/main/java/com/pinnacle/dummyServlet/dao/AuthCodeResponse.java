package com.pinnacle.dummyServlet.dao;

public class AuthCodeResponse {
    private String authcode;

    public AuthCodeResponse(String authcode) {
        this.authcode = authcode;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }
}
