package com.dev.jaskiewicz.mobilephones.data.model;


public class MobilePhone {

    private int id;
    private String producer;
    private String model;
    private String androidVersion;
    private String www;

    public MobilePhone(int id,
                       String producer,
                       String model,
                       String androidVersion,
                       String www) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.www = www;
    }

    public int getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getWWW() {
        return www;
    }
}
