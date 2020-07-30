package com.o4care.nurse.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客户信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerInfo {
    private String name;
    private int age;
    private String address;

    @JsonProperty(value = "id_number")
    private String idNumber;

    @JsonProperty(value = "img_url")
    private String imgUrl;

    @JsonProperty(value = "done_hour_mon")
    private int doneHourMon;

    @JsonProperty(value = "total_hour_mon")
    private int totalHourMon;

    @JsonProperty(value = "next_task")
    private String nextTask;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getDoneHourMon() {
        return this.doneHourMon;
    }

    public void setDoneHourMon(int doneHouMon) {
        this.doneHourMon = doneHouMon;
    }

    public int getTotalHourMon() {
        return this.totalHourMon;
    }

    public void setTotalHourMon(int totalHourMon) {
        this.totalHourMon = totalHourMon;
    }

    public String getNextTask() {
        return this.nextTask;
    }

    public void setNextTask(String nextTask) {
        this.nextTask = nextTask;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", doneHourMon=" + doneHourMon +
                ", totalHourMon=" + totalHourMon +
                ", nextTask='" + nextTask + '\'' +
                '}';
    }
}
