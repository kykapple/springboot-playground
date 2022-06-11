package com.kykapple.springbootplayground.pubsub.publisher;

import com.kykapple.springbootplayground.pubsub.domain.MessageModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

    private RedisTemplate<byte[], byte[]> redisTemplate;
    private ChannelTopic topic;

    public RedisMessagePublisher(RedisTemplate<byte[], byte[]> redisTemplate, ChannelTopic articleTopic) {
        this.redisTemplate = redisTemplate;
        this.topic = articleTopic;
    }

    @Override
    public void publish(MessageModel messageModel) {
        redisTemplate.convertAndSend(topic.getTopic(), messageModel);
    }

}
