package com.blogsite.postly.model.rest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse
{
    private final LocalDateTime timeStamp;
    private final int status;
    private final String message;
}
