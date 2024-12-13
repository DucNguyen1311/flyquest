package com.backend.flyquest.Payload;

import lombok.Data;

@Data
public class RegisterRequest {
    private String accountName;
    private String password;
    private String email;
    private String role;

    public RegisterRequest(){};
    public RegisterRequest(String email, String name, String password) {
        this.email = email;
        this.password = password;
        this.accountName = name;
        this.role = "USER";
    }
}