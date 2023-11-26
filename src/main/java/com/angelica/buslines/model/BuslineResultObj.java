package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuslineResultObj {

    @JsonProperty("LineNumber")
    private String lineNumber;
    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    public String getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }
    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

    @Override
    public String toString() {
        return "ResultObj{" +
                "lineNumber='" + lineNumber + '\'' +
                ", journeyPatternPointNumber='" + journeyPatternPointNumber + '\'' +
                '}';
    }
}
