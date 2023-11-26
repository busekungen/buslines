package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusStopResponse {

    @JsonProperty("StatusCode")
    int statusCode;
    @JsonProperty("Message")
    String message;
    @JsonProperty("ExecutionTime")
    int executionTime;
    @JsonProperty("ResponseData")
    BusStopResponseData busStopResponseData;

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }
    public BusStopResponseData getBusStopResponseData() {
        return busStopResponseData;
    }
    public void setBusStopResponseData(BusStopResponseData busStopResponseData) {
        this.busStopResponseData = busStopResponseData;
    }

    @Override
    public String toString() {
        return "BusStopResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", executionTime=" + executionTime +
                ", busStopResponseData=" + busStopResponseData +
                '}';
    }
}
