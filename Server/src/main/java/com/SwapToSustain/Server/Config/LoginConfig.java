package com.SwapToSustain.Server.Config;

import com.SwapToSustain.Server.Util.Timeout;

import java.util.concurrent.TimeUnit;

public class LoginConfig {

    public static final Timeout GENERAL_TOKEN_EXPIRATION = new Timeout(1, TimeUnit.HOURS);

}
