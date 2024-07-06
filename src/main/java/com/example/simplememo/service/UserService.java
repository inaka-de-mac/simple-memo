package com.example.simplememo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplememo.controller.SignInRequest;
import com.example.simplememo.controller.SignUpRequest;
import com.example.simplememo.mapper.UserMapper;
import com.example.simplememo.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void signup(SignUpRequest request) {
        System.out.println("receive request");
        User user = userMapper.findByEmail(request.getEmail());
        if (user != null) {
            throw new RuntimeException("入力されたメールアドレスは既に登録されています。");
        }
        try {
            userMapper.insertMemoUser(request);
        } catch (Exception e) {
            throw new RuntimeException("登録に失敗しました。入力内容を確認してください。");
        }
    }

    public User signin(SignInRequest request) {
        System.out.println("receive request");
        User user = userMapper.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found with email: " + request.getEmail());
        } else if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password for user: " + request.getEmail());
        }
        return user;
    }

    public void deleteUser(String email) {
        System.out.println("receive request");
        try {
            userMapper.deleteUser(email);
        } catch (Exception e) {
            throw new RuntimeException("削除に失敗しました。");
        }
    }
}
