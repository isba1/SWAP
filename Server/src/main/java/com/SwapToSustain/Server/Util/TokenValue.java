package com.SwapToSustain.Server.Util;

import com.SwapToSustain.Server.Util.Timeout;
import lombok.Getter;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Getter
public class TokenValue {

    // The unique identifier for the user
    private final String userEmail;

    // Timestamp when the token was issued
    private final Instant issuedAt;

    // Timeout value
    private final Timeout timeout;

    // A unique identifier for the token
    private final String UUID;

    public TokenValue(String userEmail, Timeout timeout) {
        this.userEmail = userEmail;
        this.issuedAt = Instant.now();
        this.timeout = timeout;
        this.UUID = java.util.UUID.randomUUID().toString();
    }
}
