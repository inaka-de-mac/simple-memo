package com.example.simplememo.mapper;

import com.example.simplememo.controller.SignUpRequest;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.simplememo.model.User;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users (email, password, user_name, created_at, updated_at) VALUES (#{email}, #{password}, #{userName}, NOW(), NOW())")
    void insertMemoUser(SignUpRequest request);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

    @Delete("DELETE FROM users WHERE email = #{email}")
    int deleteUser(String email); // 影響を受けた行数を返す
}
