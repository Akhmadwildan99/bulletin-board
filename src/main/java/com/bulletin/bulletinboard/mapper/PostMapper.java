package com.bulletin.bulletinboard.mapper;

import com.bulletin.bulletinboard.model.PostSummary;
import org.apache.ibatis.annotations.*;

import java.time.Instant;
import java.util.List;

@Mapper
public interface PostMapper {

   @Select("SELECT p.id, p.title, p.author, p.created_date, p.modified_date, COUNT(v.id) AS views from " +
           "posts p  LEFT JOIN public.viewers v on p.id = v.post_id" +
           " group by p.id ORDER BY p.id LIMIT #{limit} OFFSET #{offset}  ")
   List<PostSummary> getPostSummary(int limit, int offset);
}
