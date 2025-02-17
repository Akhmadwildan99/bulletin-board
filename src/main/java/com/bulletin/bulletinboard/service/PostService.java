package com.bulletin.bulletinboard.service;

import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostSummary;

import java.util.List;

public interface PostService {
    List<PostSummary> getPostsSummary(int pageNumber, int pageSiz);
    Post getPostById(Long id);
    void savePost(Post post);
    void saveView(Long id);

    void updatePost(Post post, String keyUpdate);
    String generateKeyUpdatePost(Long id,String password);
    void softDeletePost(Long id, String password);
}
