package com.kykapple.springbootplayground.pubsub.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kykapple.springbootplayground.pubsub.domain.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            MessageModel messageModel = objectMapper.readValue(message.getBody(), MessageModel.class);

            log.info("Received Article Message: " + messageModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
