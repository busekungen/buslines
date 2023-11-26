package com.angelica.buslines;

import com.angelica.buslines.service.BusStopCountingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuslinesApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BuslinesApplication.class, args);
		var busStopCountingService = context.getBean(BusStopCountingService.class);
		busStopCountingService.createTopListWithStopNames();
	}
}
