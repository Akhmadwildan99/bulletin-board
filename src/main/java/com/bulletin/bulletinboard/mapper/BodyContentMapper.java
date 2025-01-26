package com.bulletin.bulletinboard.mapper;

import com.bulletin.bulletinboard.model.BodyContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BodyContentMapper {
    @Select("SELECT * FROM body_contents where post_id = #{postId}")
    BodyContent getBodyContentByPostId(Long postId);
}
