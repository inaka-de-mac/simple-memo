package com.example.simplememo.mapper;

import com.example.simplememo.model.Memo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemoMapper {
    @Select("SELECT * FROM memos WHERE user_id = #{userId}")
    List<Memo> getMemosByUserId(int userId);

    @Select("SELECT * FROM memos WHERE id = #{id} AND user_id = #{userId}")
    Memo findMemo(@Param("userId") int userId, @Param("id") int id);

    @Insert("INSERT INTO memos(user_id, title, content, created_at, updated_at) VALUES(#{userId}, #{memo.title}, #{memo.content}, #{memo.createdAt}, #{memo.updatedAt})")
    void insertMemo(@Param("userId") int userId, @Param("memo") Memo memo);

    @Update("UPDATE memos SET title=#{memo.title}, content=#{memo.content}, updated_at=#{memo.updatedAt} WHERE id=#{id} AND user_id = #{userId}")
    void updateMemo(@Param("userId") int userId, @Param("id") int id, @Param("memo") Memo memo);

    @Delete("DELETE FROM memos WHERE id=#{id} AND user_id = #{userId}")
    void deleteMemo(@Param("userId") int userId, @Param("id") int id);
}