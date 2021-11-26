package com.example.unorpproject.model;

import java.util.Date;

public class Result<T> {
    private String message;
    private T response;
    private Date timeStamp;

    public Result(String message, T response, Date timeStamp) {
        this.message = message;
        this.response = response;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", response=" + response +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
