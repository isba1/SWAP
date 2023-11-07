package com.SwapToSustain.Server.Util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

    private JwtUtil() {
        // cannot instantiate this class
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = "c2hvcOWvhumRsDotPmY0ODQxYWNjZjI0c2Q0M2Rk".getBytes();
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    public static String createToken(TokenValue tokenValue) {
        String subject = generatePayload(tokenValue);
        return Jwts.builder()
                .setPayload(subject)
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
    }

    private static String generatePayload(TokenValue tokenValue) {
        return JSON.toJSONString(tokenValue);
    }

    public static boolean verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token).getBody();
            return true;
        }  catch (Exception e) {
            // token is invalid
            // ignored
        }
        return false;
    }

}