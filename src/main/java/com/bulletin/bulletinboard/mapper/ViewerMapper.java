package com.bulletin.bulletinboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ViewerMapper {

    @Select("SELECT COUNT(id) FROM viewers WHERE post_id = #{postId}")
    Long countByPostId(Long postId);

    @Insert("INSERT INTO viewers (post_id, created_date) VALUES (#{postId}, now())")
    void insert(@Param("postId") Long postId);
}
