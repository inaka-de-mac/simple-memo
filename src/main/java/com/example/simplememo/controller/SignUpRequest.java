package com.example.simplememo.controller;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String userName;
}
