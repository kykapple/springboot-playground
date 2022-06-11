package com.kykapple.springbootplayground.pubsub.presentation;

import com.kykapple.springbootplayground.pubsub.domain.MessageModel;
import com.kykapple.springbootplayground.pubsub.publisher.MessagePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubController {

    private MessagePublisher messagePublisher;

    public PubSubController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/api/pubsub")
    public void publishMessage(@RequestBody MessageModel messageModel) {
        messagePublisher.publish(messageModel);
    }

}
