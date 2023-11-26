package com.angelica.buslines.service;

import com.angelica.buslines.dto.BuslineDTO;
import com.angelica.buslines.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties" , properties="sl.api.key=key")
class BusStopCountingServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusStopCountingService service;

    @Test
    void getLists_returnsExpectedSizeAndLineNr_Success() {
        var buslinesResponseMock = getBuslinesResponseMock(createResultObjList());
        var top2buslines = service.getTopBuslines(buslinesResponseMock, 2);

        assertThat(top2buslines)
                .hasSize(2)
                .extracting(Busline::lineNumber)
                .containsExactlyInAnyOrder("2", "4");

        var busstopResponse = getBusStopResponseMock(createBusStopResultObjList());
        var buslineDtos = service.createBuslineDtosWithStopNames(busstopResponse, top2buslines);
        assertThat(buslineDtos)
                .hasSize(2)
                .extracting(BuslineDTO::lineNr)
                .containsExactlyInAnyOrder("2", "4");
    }

    @Test
    void testUriBuilder_returnsExceptedString_Success() {
        var uri = "https://api.sl.se/api2/LineData.json";
        var expectedUri = "https://api.sl.se/api2/LineData.json?model=jour&DefaultTransportModeCode=BUS&key=key";

        var params = new LinkedMultiValueMap<String, String>();
        params.add("model", "jour");
        params.add("DefaultTransportModeCode", "BUS");
        params.add("key", "key");

        var uriString = service.getUriString(params, uri);
        Assertions.assertEquals(expectedUri, uriString);
    }

    private BuslinesResponse getBuslinesResponseMock(List<BuslineResultObj> buslineResultObj) {
        var buslinesResponse = new BuslinesResponse();
        var responseData = new BuslineResponseData();
        responseData.setResultObjs(buslineResultObj);
        buslinesResponse.setResponseData(responseData);
        return buslinesResponse;
    }

    private BusStopResponse getBusStopResponseMock(List<BusStopResultObj> busStopResultObjs) {
        var busstopResponse = new BusStopResponse();
        var responseData = new BusStopResponseData();
        responseData.setBusStopResultObjs(busStopResultObjs);
        busstopResponse.setBusStopResponseData(responseData);
        return busstopResponse;
    }

    private List<BuslineResultObj> createResultObjList(){
        List<BuslineResultObj> list = new ArrayList<>();
        list.add(createResultObj("1", "12345"));
        list.add(createResultObj("2", "22222"));
        list.add(createResultObj("2", "33333"));
        list.add(createResultObj("3", "12345"));
        list.add(createResultObj("4", "22222"));
        list.add(createResultObj("4", "33333"));
        list.add(createResultObj("4", "44444"));
        list.add(createResultObj("4", "55555"));
        list.add(createResultObj("5", "12345"));
        return list;
    }

    private List<BusStopResultObj> createBusStopResultObjList() {
        List<BusStopResultObj> list = new ArrayList<>();
        list.add(createBusStopResultObj("Hållplats A", "12345"));
        list.add(createBusStopResultObj("Hållplats B", "22222"));
        list.add(createBusStopResultObj("Hållplats C", "33333"));
        list.add(createBusStopResultObj("Hållplats D", "44444"));
        list.add(createBusStopResultObj("Hållplats E", "55555"));
        list.add(createBusStopResultObj("Hållplats F", "66666"));
        return list;
    }

    private BuslineResultObj createResultObj(String lineNumber, String journeyPatternPointNumber) {
        var resultObj = new BuslineResultObj();
        resultObj.setLineNumber(lineNumber);
       // var stopNr = new Random().nextInt(99999 - 10000);
        resultObj.setJourneyPatternPointNumber(journeyPatternPointNumber);
        return resultObj;
    }

    private BusStopResultObj createBusStopResultObj(String stopPointName, String stopPointNumber) {
        var resultObj = new BusStopResultObj();
        resultObj.setStopPointName(stopPointName);
        resultObj.setStopPointNumber(stopPointNumber);
        return resultObj;
    }
}