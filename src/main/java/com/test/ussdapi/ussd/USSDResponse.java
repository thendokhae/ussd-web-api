package com.test.ussdapi.ussd;

import javax.persistence.Entity;

public class USSDResponse {
    private String sessionId;
    private String message;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
