package com.booleanuk.api.responses;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse extends Response<Map<String, String>> {

    public ErrorResponse(String message) {
        super();
        this.status = "error";
        Map<String, String> reply = new HashMap<>();
        reply.put("message", message);
        this.data = reply;
    }

}