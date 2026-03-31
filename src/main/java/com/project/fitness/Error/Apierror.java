package com.project.fitness.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Apierror {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public Apierror(LocalDateTime timeStamp, String error, HttpStatus statusCode) {
        this.timeStamp = timeStamp;
        this.error = error;
        this.statusCode = statusCode;
    }

    public Apierror() {
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
