package com.ibmmqbasics.topicqueuelistener.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QueueMessage {

    @NotEmpty(message = "title should not be empty")
    private String title;

    @NotEmpty(message = "queueMessage should not be empty")
    private String queueMessage;
}
