package com.bulletin.bulletinboard.mapper;

import com.bulletin.bulletinboard.model.BodyContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BodyContentMapper {
    @Select("SELECT * FROM body_contents where post_id = #{postId}")
    BodyContent getBodyContentByPostId(Long postId);

    @Insert("INSERT INTO body_contents (post_id, content, created_date) VALUES (#{postId}, #{bodyContent.content}, now())")
    void insert(@Param("bodyContent") BodyContent bodyContent, @Param("postId") Long postId);
}
