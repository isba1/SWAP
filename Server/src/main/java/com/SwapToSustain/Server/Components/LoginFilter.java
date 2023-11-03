package com.SwapToSustain.Server.Components;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginFilter implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    @Autowired
    private TokenInterface tokenInterface;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter(AUTHORIZATION_HEADER_KEY);
        }
        // if token is not empty, then verify it
        if (StrUtil.isNotEmpty(token)) {
            try {
                if (tokenInterface.verifyToken(token)) {
                    // allow the controller
                    return true;
                }
                response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
                return false;
            } catch (Exception e) {
                response.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
                return false;
            }
        }
        return true;
    }
}
