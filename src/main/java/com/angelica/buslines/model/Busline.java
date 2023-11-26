package com.angelica.buslines.model;

import java.util.List;

public record Busline(String lineNumber,
               List<String> stops) {
    public int numberOfStops() {
        return stops.size();
    }
}