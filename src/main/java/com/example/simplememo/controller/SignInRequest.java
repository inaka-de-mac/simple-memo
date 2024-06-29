package com.example.simplememo.controller;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
