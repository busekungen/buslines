package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BusStopResponseData {

    @JsonProperty("Version")
    String version;
    @JsonProperty("Type")
    String type;
    @JsonProperty("Result")
    List<BusStopResultObj> busStopResultObjs;

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<BusStopResultObj> getBusStopResultObjs() {
        return busStopResultObjs;
    }
    public void setBusStopResultObjs(List<BusStopResultObj> busStopResultObjs) {
        this.busStopResultObjs = busStopResultObjs;
    }

    @Override
    public String toString() {
        return "BusStopResponseData{" +
                "version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", busStopResultObjs=" + busStopResultObjs +
                '}';
    }
}
