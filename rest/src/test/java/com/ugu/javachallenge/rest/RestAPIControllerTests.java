package com.ugu.javachallenge.rest;

import com.ugu.javachallenge.rest.controller.RestAPIController;
import com.ugu.javachallenge.rest.data.Calculation;
import com.ugu.javachallenge.rest.data.CalculationRestResponse;
import com.ugu.javachallenge.rest.data.OperationType;
import com.ugu.javachallenge.rest.service.KafkaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestAPIControllerTests {

    @Test
    void testSuccessfulSumRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);

        Calculation expectedCalculation = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);
        expectedCalculation.setResult(new BigDecimal(4));
        CalculationRestResponse expectedResponse = new CalculationRestResponse(new BigDecimal(4));

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(expectedCalculation);

        CalculationRestResponse restResponse = restAPIController.getSumResult(new BigDecimal(1), new BigDecimal(3));
        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

    @Test
    void testFailedSumRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);
        CalculationRestResponse expectedResponse = new CalculationRestResponse(null);

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(null);

        CalculationRestResponse restResponse = restAPIController.getSumResult(new BigDecimal(1), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

    @Test
    void testSuccessfulSubtractionRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);

        Calculation expectedCalculation = new Calculation(new BigDecimal(4), new BigDecimal(3), OperationType.SUM);
        expectedCalculation.setResult(new BigDecimal(1));
        CalculationRestResponse expectedResponse = new CalculationRestResponse(new BigDecimal(1));

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(expectedCalculation);

        CalculationRestResponse restResponse = restAPIController.getSubtractionResult(new BigDecimal(4), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

    @Test
    void testFailedSubtractionRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);
        CalculationRestResponse expectedResponse = new CalculationRestResponse(null);

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(null);

        CalculationRestResponse restResponse = restAPIController.getSubtractionResult(new BigDecimal(4), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }


    @Test
    void testSuccessfulMultiplicationRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);

        Calculation expectedCalculation = new Calculation(new BigDecimal(2), new BigDecimal(3), OperationType.SUM);
        expectedCalculation.setResult(new BigDecimal(6));
        CalculationRestResponse expectedResponse = new CalculationRestResponse(new BigDecimal(6));

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(expectedCalculation);

        CalculationRestResponse restResponse = restAPIController.getMultiplicationResult(new BigDecimal(2), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

    @Test
    void testFailedMultiplicationRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);
        CalculationRestResponse expectedResponse = new CalculationRestResponse(null);

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(null);

        CalculationRestResponse restResponse = restAPIController.getMultiplicationResult(new BigDecimal(2), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }


    @Test
    void testSuccessfulDivisionRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);

        Calculation expectedCalculation = new Calculation(new BigDecimal(9), new BigDecimal(3), OperationType.SUM);
        expectedCalculation.setResult(new BigDecimal(3));
        CalculationRestResponse expectedResponse = new CalculationRestResponse(new BigDecimal(3));

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(expectedCalculation);

        CalculationRestResponse restResponse = restAPIController.getDivisionResult(new BigDecimal(9), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

    @Test
    void testFailedDivisionRequest() throws Exception {
        KafkaService kafkaService = mock(KafkaService.class);
        RestAPIController restAPIController = new RestAPIController(kafkaService);
        CalculationRestResponse expectedResponse = new CalculationRestResponse(null);

        when(kafkaService.sendAndReceive(any(Calculation.class)))
                .thenReturn(null);

        CalculationRestResponse restResponse = restAPIController.getDivisionResult(new BigDecimal(9), new BigDecimal(3));

        assert Objects.equals(restResponse.getResult(), expectedResponse.getResult());
    }

}
