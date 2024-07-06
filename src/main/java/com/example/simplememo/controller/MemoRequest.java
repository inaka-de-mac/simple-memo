package com.example.simplememo.controller;

import lombok.Data;

@Data
public class MemoRequest {
    private String title;
    private String content;
}
