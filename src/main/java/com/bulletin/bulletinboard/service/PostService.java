package com.bulletin.bulletinboard.service;

import com.bulletin.bulletinboard.model.PostSummary;

import java.util.List;

public interface PostService {
    public List<PostSummary> getPostsSummary(int pageNumber, int pageSiz);
}
