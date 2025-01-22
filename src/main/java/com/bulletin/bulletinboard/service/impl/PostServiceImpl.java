package com.bulletin.bulletinboard.service.impl;

import com.bulletin.bulletinboard.mapper.PostMapper;
import com.bulletin.bulletinboard.model.PostSummary;
import com.bulletin.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Override
    public List<PostSummary> getPostsSummary(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return postMapper.getPostSummary(pageSize, offset);
    }
}
