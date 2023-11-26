package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuslinesResponse {

    @JsonProperty("StatusCode")
    int statusCode;
    @JsonProperty("Message")
    String message;
    @JsonProperty("ExecutionTime")
    int executionTime;
    @JsonProperty("ResponseData")
    BuslineResponseData buslineResponseData;

    public BuslinesResponse() {
    }

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
    public BuslineResponseData getResponseData() {
        return buslineResponseData;
    }
    public void setResponseData(BuslineResponseData buslineResponseData) {
        this.buslineResponseData = buslineResponseData;
    }

    @Override
    public String toString() {
        return "BuslinesResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", executionTime=" + executionTime +
                ", buslineResponseData=" + buslineResponseData +
                '}';
    }
}
