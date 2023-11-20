package com.SwapToSustain.Server.Util;

import com.SwapToSustain.Server.Util.Timeout;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
public class TokenValue {

    // The unique identifier for the user
    private final String userName;

    // Timestamp when the token was issued
    private final Instant issuedAt;

    // Timeout value
    private final Timeout timeout;

    // A unique identifier for the token
    private final String UUID;

    public TokenValue(String userName, Timeout timeout) {
        this.userName = userName;
        this.issuedAt = Instant.now();
        this.timeout = timeout;
        this.UUID = java.util.UUID.randomUUID().toString();
    }
}
