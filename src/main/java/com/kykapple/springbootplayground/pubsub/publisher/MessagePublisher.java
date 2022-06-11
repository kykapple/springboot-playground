package com.kykapple.springbootplayground.pubsub.publisher;

import com.kykapple.springbootplayground.pubsub.domain.MessageModel;

public interface MessagePublisher {

    void publish(MessageModel messageModel);

}
