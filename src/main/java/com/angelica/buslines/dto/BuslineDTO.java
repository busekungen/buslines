package com.angelica.buslines.dto;

import java.util.List;

public record BuslineDTO(String lineNr, List<BusStopDTO> stops) {
}
