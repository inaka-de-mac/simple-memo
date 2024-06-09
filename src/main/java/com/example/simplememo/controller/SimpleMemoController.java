package com.example.simplememo.controller;

import com.example.simplememo.model.Memo;
import com.example.simplememo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class SimpleMemoController {
    private final MemoService memoService;

    public SimpleMemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 全てのMemoレコードを取得するエンドポイント
    @GetMapping
    public List<Memo> getAllMemos() {
        return memoService.findAll();
    }

    // 新しいMemoレコードを作成するエンドポイント
    @PostMapping
    public int createMemo(@RequestBody String content) {
        // リクエストの内容から新しいMemoオブジェクトを作成し、Serviceに挿入を依頼
        Memo newMemo = new Memo(0, content, LocalDateTime.now(), LocalDateTime.now());
        memoService.insert(newMemo);
        // 作成したMemoのIDを返す
        return newMemo.getId();
    }

    // 指定されたIDのMemoレコードを更新するエンドポイント
    @PutMapping("/{id}")
    public void updateMemo(@PathVariable int id, @RequestBody String content) {
        // 指定されたIDのMemoレコードをServiceから取得し、内容を更新
        Memo memoToUpdate = memoService.findById(id);
        if (memoToUpdate != null) {
            memoToUpdate.setContent(content);
            memoToUpdate.setUpdatedAt(LocalDateTime.now());
            memoService.update(memoToUpdate);
        }
    }

    // 指定されたIDのMemoレコードを削除するエンドポイント
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable int id) {
        // 指定されたIDのMemoレコードをServiceから削除
        memoService.delete(id);
    }
}
