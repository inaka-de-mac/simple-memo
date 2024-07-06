package com.example.simplememo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplememo.model.Memo;
import com.example.simplememo.service.MemoService;

@RestController
@RequestMapping("/api/users/{user_id}/memos")
public class MemoController {
    @Autowired
    private MemoService memoService;

    @GetMapping
    public ResponseEntity<List<Memo>> getMemosByUserId(@PathVariable("user_id") int userId) {
        System.out.println("receive request");
        return ResponseEntity.ok(memoService.getMemosByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Integer> createMemo(@PathVariable("user_id") int userId, @RequestBody MemoRequest request) {
        try {
            System.out.println("receive request... userId:" + userId + ", request:" + request);
            memoService.insertMemo(userId, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMemo(@PathVariable("user_id") int userId, @PathVariable("id") int id,
            @RequestBody MemoRequest request) {
        try {
            memoService.updateMemo(userId, id, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable("user_id") int userId, @PathVariable("id") int id) {
        try {
            memoService.deleteMemo(userId, id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
