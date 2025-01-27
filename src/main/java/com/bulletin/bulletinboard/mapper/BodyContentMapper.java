package com.bulletin.bulletinboard.mapper;

import com.bulletin.bulletinboard.model.BodyContent;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BodyContentMapper {
    @Select("SELECT * FROM body_contents where post_id = #{postId}")
    BodyContent getBodyContentByPostId(Long postId);

    @Insert("INSERT INTO body_contents (post_id, content, created_date) VALUES (#{postId}, #{bodyContent.content}, now())")
    void insert(@Param("bodyContent") BodyContent bodyContent, @Param("postId") Long postId);

    @Update("UPDATE body_contents SET content=#{bodyContent.content} where id = #{bodyContent.id} and post_id = #{postId}")
    void update(@Param("bodyContent") BodyContent bodyContent, @Param("postId") Long postId);
}
