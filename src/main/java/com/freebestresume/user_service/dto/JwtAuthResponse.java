package com.freebestresume.user_service.dto;

import com.freebestresume.user_service.constant.CommonVariables;

public class JwtAuthResponse {

    private String accessToken;
    private final String tokenType = CommonVariables.BEARER;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
