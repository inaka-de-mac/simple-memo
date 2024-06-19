package com.example.simplememo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/memos")
public class SimpleMemoController {
    private final MemoService memoService;
    private static final Logger logger = LoggerFactory.getLogger(SimpleMemoController.class);

    public SimpleMemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 全てのMemoレコードを取得するエンドポイント
    @GetMapping
    public ResponseEntity<List<Memo>> getAllMemos() {
        logger.info("Retrieving all memos");
        return ResponseEntity.ok(memoService.findAll());
    }

    // 新しいMemoレコードを作成するエンドポイント
    @PostMapping
    public ResponseEntity<Integer> createMemo(@RequestBody String content) {
        LocalDateTime now = LocalDateTime.now();
        Memo newMemo = new Memo(0, content, now, now);
        logger.info("Created memo: {}", newMemo);
        memoService.insert(newMemo);
        return ResponseEntity.ok(newMemo.getId());
    }

    // 指定されたIDのMemoレコードを更新するエンドポイント
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMemo(@PathVariable int id, @RequestBody String content) {
        logger.info("Received content for memo ID {}: {}", id, content);
        LocalDateTime now = LocalDateTime.now();
        Memo memoToUpdate = memoService.findById(id);
        if (memoToUpdate != null) {
            memoToUpdate.setContent(content);
            memoToUpdate.setUpdatedAt(now);
            memoService.update(memoToUpdate);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 指定されたIDのMemoレコードを削除するエンドポイント
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable int id) {
        logger.info("Deleting memo with ID: {}", id);
        if (memoService.findById(id) != null) {
            memoService.delete(id);
            return ResponseEntity.noContent().build(); // 物理削除は204
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
