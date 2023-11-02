package com.SwapToSustain.Server.Components;


import com.SwapToSustain.Server.Util.TokenValue;

public interface TokenInterface {

    String generateToken(TokenValue tokenValue);

    boolean verifyToken(String token);

}
