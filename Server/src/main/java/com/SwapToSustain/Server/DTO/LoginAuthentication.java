package com.SwapToSustain.Server.DTO;


import lombok.Data;

@Data
public class LoginAuthentication {

    private boolean loginSuccess;

    private String tokenString;

}
