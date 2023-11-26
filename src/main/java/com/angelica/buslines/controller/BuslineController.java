package com.angelica.buslines.controller;

import com.angelica.buslines.service.BusStopCountingService;
import com.angelica.buslines.dto.BuslineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuslineController {

    @Autowired
    BusStopCountingService busStopCountingService;

    @GetMapping("/buslines")
    List<BuslineDTO> getTop10Buslines() {
        return busStopCountingService.getListWithBuslineDtos();
    }
}
