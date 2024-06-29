package com.example.simplememo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplememo.model.User;
import com.example.simplememo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
    try {
      userService.signup(request);
      return new ResponseEntity<>(HttpStatus.CREATED); // 201
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<User> signin(@RequestBody SignInRequest request) {
    try {
      User user = userService.signin(request);
      return new ResponseEntity<>(user, HttpStatus.OK); // 200
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
    }
  }

  @DeleteMapping("/account")
  public ResponseEntity<Void> deleteUser(@RequestBody String email) {
    try {
      userService.deleteUser(email);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
  }
}
