package com.example.simplememo.service;

import java.time.LocalDateTime;

import com.example.simplememo.controller.MemoRequest;
import com.example.simplememo.mapper.MemoMapper;
import com.example.simplememo.model.Memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public List<Memo> getMemosByUserId(int userId) {
        return memoMapper.getMemosByUserId(userId);
    }

    public Memo findMemo(int userID, int id) {
        return memoMapper.findMemo(userID, id);
    }

    public void insertMemo(int userId, MemoRequest request) {
        Memo memo = new Memo(0, userId, request.getTitle(), request.getContent(), LocalDateTime.now(),
                LocalDateTime.now());
        try {
            memoMapper.insertMemo(userId, memo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("メモの登録に失敗しました。");
        }
    }

    public void updateMemo(int userId, int id, MemoRequest request) {
        try {
            Memo memo = memoMapper.findMemo(userId, id);
            if (memo == null) {
                throw new RuntimeException("メモが見つかりません。");
            }
            Memo updateMemo = new Memo(id, userId, request.getTitle(), request.getContent(), memo.getCreatedAt(),
                    LocalDateTime.now());
            memoMapper.updateMemo(userId, id, updateMemo);
        } catch (Exception e) {
            throw new RuntimeException("メモの更新に失敗しました。");
        }
    }

    public void deleteMemo(int userId, int id) {
        try {
            memoMapper.deleteMemo(userId, id);
        } catch (Exception e) {
            throw new RuntimeException("メモの削除に失敗しました。");
        }
    }
}
