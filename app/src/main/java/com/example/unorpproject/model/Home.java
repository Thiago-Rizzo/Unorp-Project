package com.example.unorpproject.model;

public class Home {
    private long id;
    private User user;
    private String deviceName;
    private long usageTime;
    private long power;
    private long quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(long usageTime) {
        this.usageTime = usageTime;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", user=" + user +
                ", deviceName='" + deviceName + '\'' +
                ", usageTime=" + usageTime +
                ", power=" + power +
                ", quantity=" + quantity +
                '}';
    }
}
