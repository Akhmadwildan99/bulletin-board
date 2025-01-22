package com.bulletin.bulletinboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ViewerMapper {

    @Select("SELECT COUNT(id) FROM viewers WHERE post_id = #{postId}")
    Long countByPostId(Long postId);
}
