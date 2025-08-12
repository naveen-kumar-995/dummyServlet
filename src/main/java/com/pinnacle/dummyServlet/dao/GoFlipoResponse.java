package com.pinnacle.dummyServlet.dao;


public class GoFlipoResponse<T> {
    private boolean status;
    private String message;
    private String error_code;
    private T data;

    public GoFlipoResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GoFlipoResponse(boolean status, String message, String error_code, T data) {
        this.status = status;
        this.message = message;
        this.error_code = error_code;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}

