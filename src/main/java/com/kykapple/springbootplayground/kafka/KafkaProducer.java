package com.kykapple.springbootplayground.kafka;

import com.kykapple.springbootplayground.kafka.model.MessageModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MessageModel messageModel) {
        kafkaTemplate.send("kafka-my-topic", messageModel)
                .addCallback(new ListenableFutureCallback<>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        System.out.println("Unable to send message = " + messageModel + " due to : " + ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        System.out.println("Sent message = " + messageModel + " with offset = " + result.getRecordMetadata().offset());
                    }
                });
    }

}
