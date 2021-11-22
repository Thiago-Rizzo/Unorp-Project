package com.example.unorpproject.model;

import java.util.Date;

public class Result {
    private String message;
    private User response;
    private Date timeStamp;

    public Result(String message, User response, Date timeStamp) {
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

    public User getResponse() {
        return response;
    }

    public void setResponse(User response) {
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
