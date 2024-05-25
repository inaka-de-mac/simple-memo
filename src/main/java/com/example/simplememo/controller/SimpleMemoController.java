package com.example.simplememo.controller;

import com.example.simplememo.model.Memo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController // JSONやXMLなどのデータを返すコントローラーであることを示す
@RequestMapping("/api/memos") // 個別のメソッドに"api/memos/create"と書く必要がなくなる
public class SimpleMemoController {

  private List<Memo> allMemos = new ArrayList<Memo>();
  private int nextId = 0; // 削除されたメモIDと重複しないように次のメモのIDを保持する

  @GetMapping
  public ResponseEntity<List<Memo>> getAllMemos() {
    return ResponseEntity.ok(allMemos);
  }

  @PostMapping
  public ResponseEntity<Integer> createMemo(@RequestBody String content) {
    LocalDateTime now = LocalDateTime.now();
    Memo newMemo = new Memo(nextId++, content, now, now);
    allMemos.add(newMemo);
    return ResponseEntity.ok(newMemo.getId());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateMemo(@PathVariable int id, @RequestBody String content) {
    LocalDateTime now = LocalDateTime.now();
    Memo memoToUpdate = findMemoById(id);
    if (memoToUpdate != null) {
      memoToUpdate.setContent(content);
      memoToUpdate.setUpdatedAt(now);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMemo(@PathVariable int id) {
    Memo memoToDelete = findMemoById(id);
    if (memoToDelete != null) {
      allMemos.remove(memoToDelete);
      return ResponseEntity.noContent().build(); // 物理削除は204
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private Memo findMemoById(int id) {
    for (Memo memo : allMemos) {
      if (memo.getId() == id) {
        return memo;
      }
    }
    return null;
  }

}
