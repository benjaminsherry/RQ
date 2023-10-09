package com.example.rqchallenge.employees.entity;

public class SimpleData {

    private String status;
    private Object data;
    private String message;

    public SimpleData(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public SimpleData() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
