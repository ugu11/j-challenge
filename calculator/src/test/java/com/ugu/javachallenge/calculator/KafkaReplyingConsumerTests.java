package com.ugu.javachallenge.calculator;

import com.ugu.javachallenge.calculator.data.Calculation;
import com.ugu.javachallenge.calculator.data.OperationType;
import com.ugu.javachallenge.calculator.service.KafkaReplyingConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class KafkaReplyingConsumerTests {

    @Test
    void testSuccessfulConsumer() throws InterruptedException {
        KafkaReplyingConsumer kafkaReplyingConsumer = new KafkaReplyingConsumer();

        // Mock Calculations
        Calculation mockCalculation = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);
        Calculation expectedResult = new Calculation(new BigDecimal(1), new BigDecimal(3), OperationType.SUM);
        expectedResult.setResult(new BigDecimal(4));

        Calculation response = kafkaReplyingConsumer.listen(mockCalculation);

        assert response.equals(expectedResult);
    }

}