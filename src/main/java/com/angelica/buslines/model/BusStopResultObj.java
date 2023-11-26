package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusStopResultObj {

    @JsonProperty("StopPointNumber")
    private String stopPointNumber;
    @JsonProperty("StopPointName")
    private String stopPointName;

    public String getStopPointNumber() {
        return stopPointNumber;
    }
    public void setStopPointNumber(String stopPointNumber) {
        this.stopPointNumber = stopPointNumber;
    }
    public String getStopPointName() {
        return stopPointName;
    }
    public void setStopPointName(String stopPointName) {
        this.stopPointName = stopPointName;
    }

    @Override
    public String toString() {
        return "BusStopResultObj{" +
                "stopPointNumber='" + stopPointNumber + '\'' +
                ", stopPointName='" + stopPointName + '\'' +
                '}';
    }
}
