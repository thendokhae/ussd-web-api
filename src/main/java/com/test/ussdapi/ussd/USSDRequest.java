package com.test.ussdapi.ussd;

import javax.validation.constraints.NotBlank;

public class USSDRequest {
    @NotBlank(message = "Session is mandatory")
    private String sessionId;
    @NotBlank(message = "msisdn is mandatory")
    private String msisdn;
    private String userEntry;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String userEntry) {
        this.userEntry = userEntry;
    }
}
