package com.ugu.javachallenge.rest;

import com.ugu.javachallenge.rest.data.Calculation;
import com.ugu.javachallenge.rest.data.OperationType;
import com.ugu.javachallenge.rest.service.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.math.BigDecimal;
import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class KafkaServiceTests {
    @Test
    void testSuccessfulSendAndReceive() throws ExecutionException, InterruptedException, InstantiationException, IllegalAccessException {
        // Mock Calculations
        Calculation mockCalculation = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);
        Calculation expectedResult = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);
        expectedResult.setResult(new BigDecimal(4));

        ReplyingKafkaTemplate<String, Calculation, Calculation> mockKafkaTemplate = mock(ReplyingKafkaTemplate.class);
        KafkaService kafkaService = new KafkaService(mockKafkaTemplate);

        // Define mock Kafka calls
        RequestReplyFuture<String, Calculation, Calculation> mockSendAndReceive = mock(RequestReplyFuture.class);
        ConsumerRecord<String, Calculation> mockConsumerRecord = mock(ConsumerRecord.class);
        SendResult<String, Calculation> mockSendResult = mock(SendResult.class);
        CompletableFuture<SendResult<String, Calculation>> futureSendResult = CompletableFuture.completedFuture(mockSendResult);

        when(mockKafkaTemplate.sendAndReceive(any(ProducerRecord.class))).thenReturn(mockSendAndReceive);
        when(mockSendAndReceive.getSendFuture()).thenReturn(futureSendResult);
        when(mockSendAndReceive.get()).thenReturn(mockConsumerRecord);
        when(mockConsumerRecord.value()).thenReturn(expectedResult);

        KafkaService mockKafkaService = new KafkaService(mockKafkaTemplate);
        Calculation response = mockKafkaService.sendAndReceive(mockCalculation);

        assert response.equals(expectedResult);
    }

    @Test
    void testFailedSendAndReceive() throws ExecutionException, InterruptedException, InstantiationException, IllegalAccessException {
        // Mock Calculations
        Calculation mockCalculation = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);

        ReplyingKafkaTemplate<String, Calculation, Calculation> mockKafkaTemplate = mock(ReplyingKafkaTemplate.class);
        KafkaService kafkaService = new KafkaService(mockKafkaTemplate);

        // Define mock Kafka calls
        RequestReplyFuture<String, Calculation, Calculation> mockSendAndReceive = mock(RequestReplyFuture.class);
        ConsumerRecord<String, Calculation> mockConsumerRecord = mock(ConsumerRecord.class);
        SendResult<String, Calculation> mockSendResult = mock(SendResult.class);
        CompletableFuture<SendResult<String, Calculation>> futureSendResult = CompletableFuture.completedFuture(mockSendResult);

        when(mockKafkaTemplate.sendAndReceive(any(ProducerRecord.class))).thenReturn(mockSendAndReceive);

        KafkaService mockKafkaService = new KafkaService(mockKafkaTemplate);
        Calculation response = mockKafkaService.sendAndReceive(mockCalculation);

        assert response == null;
    }

}