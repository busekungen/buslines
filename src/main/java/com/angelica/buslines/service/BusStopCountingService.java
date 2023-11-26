package com.angelica.buslines.service;

import com.angelica.buslines.dto.BusStopDTO;
import com.angelica.buslines.model.*;
import com.angelica.buslines.dto.BuslineDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusStopCountingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sl.api.uri}")
    private String uri;

    @Value("${sl.api.key}")
    private String key;

    Logger log = LoggerFactory.getLogger(BusStopCountingService.class);
    private final List<BuslineDTO> buslineDtos = new ArrayList<>();

    public void createTopListWithStopNames() {
        log.info("Create busline dto cache");
        var buslinesResponse = getBuslinesInfo();
        var busStopsResponse = getBusStopsInfo(); // Could do in parallel but skipping
        var top10Buslines = getTopBuslines(buslinesResponse, 10);
        var buslineDtos = createBuslineDtosWithStopNames(busStopsResponse, top10Buslines);
        this.buslineDtos.clear();
        this.buslineDtos.addAll(buslineDtos);
        log.info("Done creating busline dto cache");
    }

    public List<Busline> getTopBuslines(BuslinesResponse buslinesResponse, int limit) {

        log.info("Get top {} buslines", limit);

        var buslineResultObjs = buslinesResponse.getResponseData().getResultObjs();

        var alreadyAddedStops = Collections.synchronizedSet(new HashSet<String>());

        var responsePerLineNr = buslineResultObjs.stream()
                .filter(s -> {
                    // Used to filter out already added stops (ex multiple directions)
                    var addedKey = s.getLineNumber() + s.getJourneyPatternPointNumber();
                    var notAdded = alreadyAddedStops.add(addedKey);
                    return notAdded;
                })
                .collect(Collectors.groupingBy(BuslineResultObj::getLineNumber,
                        Collectors.mapping(BuslineResultObj::getJourneyPatternPointNumber, Collectors.toList())));

        var buslines = responsePerLineNr.entrySet().stream()
                .map(e -> new Busline(e.getKey(), e.getValue()))
                .toList();

        var topBuslines = buslines.stream()
                .sorted(Comparator.comparing(Busline::numberOfStops).reversed())
                .limit(limit)
                .toList();

        return topBuslines;
    }

    public List<BuslineDTO> createBuslineDtosWithStopNames(BusStopResponse busStopResponse, List<Busline> topBuslines) {

        log.info("Create dtos with stop name");

        var busstopResultObjs = busStopResponse.getBusStopResponseData().getBusStopResultObjs();

        var busStopsNoAndNames = busstopResultObjs
                .stream()
                .collect(Collectors.toMap(BusStopResultObj::getStopPointNumber, BusStopResultObj::getStopPointName));

        var buslineDtos = topBuslines.stream()
                .map(busline -> {
                    var busStops = busline.stops().stream()
                            .map(busstopNr -> {
                                var busStopName = busStopsNoAndNames.getOrDefault(busstopNr, "Okänd hållplats");
                                return new BusStopDTO(busstopNr, busStopName);
                            })
                            .collect(Collectors.toList());
                    return new BuslineDTO(busline.lineNumber(), busStops);
                })
                .collect(Collectors.toList());

        return buslineDtos;
    }

    public List<BuslineDTO> getListWithBuslineDtos() {
        return buslineDtos;
    }

    public String getUriString(MultiValueMap<String, String> params, String uri) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParams(params)
                .build().toString();
    }

    private BuslinesResponse getBuslinesInfo() {
        var params = new LinkedMultiValueMap<String, String>();
        params.add("model", "jour");
        params.add("DefaultTransportModeCode", "BUS");
        params.add("key", key);

        var uriString = getUriString(params, uri);

        log.info("Fetching Buslines from {}", uri);

        return  restTemplate.getForObject(uriString, BuslinesResponse.class);
    }

    private BusStopResponse getBusStopsInfo() {
        var params = new LinkedMultiValueMap<String, String>();
        params.add("model", "stop");
        params.add("key", key);

        var uriString = getUriString(params, uri);

        log.info("Fetching BusStops from {}", uri);

        return restTemplate.getForObject(uriString, BusStopResponse.class);
    }
}
