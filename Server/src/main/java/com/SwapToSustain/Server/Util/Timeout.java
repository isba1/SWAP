package com.SwapToSustain.Server.Util;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class Timeout {
    private final long timeoutValue;
    private final TimeUnit timeUnit;

    public Timeout(long timeout, TimeUnit timeUnit) {
        this.timeoutValue = timeout;
        this.timeUnit = timeUnit;
    }
}
