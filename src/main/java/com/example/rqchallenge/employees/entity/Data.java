package com.example.rqchallenge.employees.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Data {

    private String status;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Employee[] data;
    private String message;

    public Data(String status, Employee[] data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Data() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee[] getData() {
        return data;
    }

    public void setData(Employee[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
