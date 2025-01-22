package com.bulletin.bulletinboard.model;

import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

public class BodyContent {
    private Long id;
    private String content;
    private Instant createdDate;
    private Instant modifiedDate;

}
