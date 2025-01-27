package com.bulletin.bulletinboard.mapper;

import com.bulletin.bulletinboard.model.BodyContent;
import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostCredential;
import com.bulletin.bulletinboard.model.PostSummary;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

   @Select("SELECT p.id, p.author, p.title, p.title_en ,p.created_date, p.modified_date, COUNT(v.id) AS views from " +
           "posts p  LEFT JOIN public.viewers v on p.id = v.post_id" +
           " group by p.id ORDER BY p.id LIMIT #{limit} OFFSET #{offset}  ")
   @Results({
           @Result(property = "id", column = "id"),
           @Result(property = "author", column = "author"),
           @Result(property = "title", column = "title"),
           @Result(property = "titleEn", column = "title_en"),
           @Result(property = "createdDate", column = "created_date"),
           @Result(property = "modifiedDate", column = "modified_date"),
           @Result(property = "views", column = "views"),
   })
   List<PostSummary> getPostSummary(int limit, int offset);

   @Select("SELECT p.id, p.title,  p.title_en,p.author, p.created_date, p.modified_date, COUNT(v.id) AS views from " +
           "posts p  LEFT JOIN public.viewers v on p.id = v.post_id " +
           "where p.id = #{id} group by p.id ORDER BY p.id")
   @Results(value = {
           @Result(property = "id", column = "id"),
           @Result(property = "author", column = "author"),
           @Result(property = "title", column = "title"),
           @Result(property = "titleEn", column = "title_en"),
           @Result(property = "createdDate", column = "created_date"),
           @Result(property = "modifiedDate", column = "modified_date"),
           @Result(property = "views", column = "views"),
           @Result(property = "bodyContent", javaType = BodyContent.class, column = "id", one = @One(select = "com.bulletin.bulletinboard.mapper.BodyContentMapper.getBodyContentByPostId"))
   })
   Optional<Post> getPostById(Long id);

   @Insert("INSERT INTO posts  (author, title, title_en,password, created_date) VALUES (#{post.author}, #{post.title},  #{post.titleEn}, #{post.password}, now())")
   @Options(useGeneratedKeys = true, keyProperty = "post.id")
   void insert(@Param("post") Post post);

   @Update("UPDATE posts SET author= #{post.author}, title= #{post.title}, title_en= #{post.titleEn},  modified_date=now() where id = #{post.id}")
   void update(@Param("post") Post post);

   @Select("SELECT p.id, p.password, p.updated_key, p.updated_key_date from posts p where p.id = #{id} ")
   @Results(value = {
           @Result(property = "id", column = "id"),
           @Result(property = "password", column = "password"),
           @Result(property = "updatedKey", column = "updated_key"),
           @Result(property = "updatedKeyDate", column = "updated_key_date")
   })
   Optional<PostCredential> getPostCredentialById(@Param("id") Long id);


   @Update("UPDATE posts SET updated_key= #{postCredential.updatedKey}, updated_key_date= now() where id = #{postCredential.id}")
   void updateKey(@Param("postCredential") PostCredential postCredential);

}
