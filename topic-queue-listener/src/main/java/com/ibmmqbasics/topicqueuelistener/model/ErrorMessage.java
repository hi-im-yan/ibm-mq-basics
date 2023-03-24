package com.ibmmqbasics.topicqueuelistener.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorMessage {

    private String origin;
    private List<String> errors = new ArrayList<>();
}
