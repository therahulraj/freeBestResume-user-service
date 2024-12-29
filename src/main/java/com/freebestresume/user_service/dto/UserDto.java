package com.freebestresume.user_service.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {

    private String emailId;

    private String password;

    public UserDto(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public UserDto() {
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

}
