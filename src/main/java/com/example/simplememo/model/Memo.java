package com.example.simplememo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Lombokの@Dataを使えばgetterとsetterの記述が不要
public class Memo {
  private int id; // メモのID
  private String content; // メモの内容
  private String createdAt; // メモの作成日時
  private String updatedAt; // メモの更新日時

  public Memo(int id, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.content = content;
    this.createdAt = formatDateTime(createdAt);
    this.updatedAt = formatDateTime(updatedAt);
  }

  // メモのIDを取得する
  public int getId() {
    return id;
  }

  // メモのIDを設定する
  public void setId(int id) {
    this.id = id;
  }

  // メモの内容を取得する
  public String getContent() {
    return content;
  }

  // メモの内容を設定する
  public void setContent(String content) {
    this.content = content;
  }

  // メモの作成日時を取得する
  public String getCreatedAt() {
    return createdAt;
  }

  // メモの作成日時を設定する
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = formatDateTime(createdAt);
  }

  // メモの更新日時を取得する
  public String getUpdatedAt() {
    return updatedAt;
  }

  // メモの更新日時を設定する
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = formatDateTime(updatedAt);
  }

  // 日付を指定のフォーマットに変換するメソッド
  private String formatDateTime(LocalDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    return dateTime.format(formatter);
  }
}
