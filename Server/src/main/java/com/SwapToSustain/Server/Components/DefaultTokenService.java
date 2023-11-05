package com.SwapToSustain.Server.Components;


import com.SwapToSustain.Server.Util.JwtUtil;
import com.SwapToSustain.Server.Util.TokenValue;
import org.springframework.stereotype.Component;


@Component
public class DefaultTokenService implements TokenInterface {
    @Override
    public String generateToken(TokenValue tokenValue) {
        return JwtUtil.createToken(tokenValue);
    }


    @Override
    public boolean verifyToken(String token) {
        return JwtUtil.verifyToken(token);
    }
}
