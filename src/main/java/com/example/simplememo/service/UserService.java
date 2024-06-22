package com.example.simplememo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplememo.controller.AuthRequest;
import com.example.simplememo.controller.SignUpRequest;
import com.example.simplememo.mapper.UserMapper;
import com.example.simplememo.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void signup(SignUpRequest request) {
        // 該当のメールアドレスが既に登録されているか確認
        User user = userMapper.findByEmail(request.getEmail());
        if (user != null) {
            throw new RuntimeException("入力されたメールアドレスは既に登録されています。");
        }
        try {
            userMapper.insertUser(request);
        } catch (Exception e) {
            throw new RuntimeException("登録に失敗しました。入力内容を確認してください。");
        }
    }

    public User signin(AuthRequest request) {
        User user = userMapper.findByEmail(request.getEmail());
        if (user.getPassword().equals(request.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("入力されたパスワードが違います。");
        }
    }

    public void deleteUser(AuthRequest request) {
        User user = userMapper.findByEmail(request.getEmail());
        if (user.getPassword().equals(request.getPassword())) {
            userMapper.deleteUser(request.getEmail());
        } else {
            throw new RuntimeException("入力されたパスワードが違います。");
        }
    }
}
