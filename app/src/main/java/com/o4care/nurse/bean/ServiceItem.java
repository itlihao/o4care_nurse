package com.o4care.nurse.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 客户信息
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceItem {
    private int id;
    private String name;
    private String manual;

    public int getId() {
        return this.id;
    }
    public void setId( int Id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getManual() {
        return this.manual;
    }
    public void setManual(String manual) {
        this.manual = manual;
    }
}
