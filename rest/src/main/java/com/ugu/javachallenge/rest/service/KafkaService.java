package com.ugu.javachallenge.rest.service;

import com.ugu.javachallenge.rest.data.Calculation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {
    String REQUEST_TOPIC = "request-topic";
    String REPLY_TOPIC = "reply-topic";

    private final ReplyingKafkaTemplate<String, Calculation, Calculation> kafkaTemplate;

    public KafkaService(ReplyingKafkaTemplate<String, Calculation, Calculation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Calculation sendAndReceive(Calculation request) throws ExecutionException, InterruptedException {
        ProducerRecord<String, Calculation> record = new ProducerRecord<String, Calculation>(REQUEST_TOPIC, request);
        // set reply topic in header
        record.headers().add(new RecordHeader(REPLY_TOPIC, REPLY_TOPIC.getBytes()));

        // post in kafka topic
        RequestReplyFuture<String, Calculation, Calculation> sendAndReceive = kafkaTemplate.sendAndReceive(record);
        // confirm if producer produced successfully
        SendResult<String, Calculation> sendResult;

        try {
            sendResult = sendAndReceive.getSendFuture().get();
        } catch (NullPointerException e) {
            return null;
        }

        // get consumer record
        ConsumerRecord<String, Calculation> consumerRecord = sendAndReceive.get();

        // return consumer value
        return consumerRecord.value();
    }
}
