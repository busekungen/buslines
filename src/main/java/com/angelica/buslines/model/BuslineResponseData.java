package com.angelica.buslines.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BuslineResponseData {
    @JsonProperty("Version")
    String version;
    @JsonProperty("Type")
    String type;
    @JsonProperty("Result")
    List<BuslineResultObj> buslineResultObjs;

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
    public List<BuslineResultObj> getResultObjs() {
        return buslineResultObjs;
    }
    public void setResultObjs(List<BuslineResultObj> buslineResultObjs) {
        this.buslineResultObjs = buslineResultObjs;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", resultObjs=" + buslineResultObjs +
                '}';
    }
}
