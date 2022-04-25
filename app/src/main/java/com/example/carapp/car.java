package com.example.carapp;

import androidx.annotation.NonNull;

public class car {
    String id;
    String model;
    String company;
    String type;
    String sunroof;

    public car() {

    }

    public car(String id,String model, String company, String type, String sunroof) {
        this.id=id;
        this.model = model;
        this.company = company;
        this.type = type;
        this.sunroof = sunroof;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSunroof() {
        return sunroof;
    }

    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    @Override
    public String toString() {
        return "\n Car :"+
                "id = "+id+'\n'+
                "company = "+company+'\n'+
                "model = "+model+'\n'+
                "type = "+type+'\n'+
                "sun roof = "+sunroof+'\n';
    }
}
