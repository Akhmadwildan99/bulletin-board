package com.bulletin.bulletinboard.service.impl;

import com.bulletin.bulletinboard.mapper.BodyContentMapper;
import com.bulletin.bulletinboard.mapper.PostMapper;
import com.bulletin.bulletinboard.mapper.ViewerMapper;
import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostSummary;
import com.bulletin.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    BodyContentMapper bodyContentMapper;

    @Autowired
    ViewerMapper viewerMapper;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public List<PostSummary> getPostsSummary(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return postMapper.getPostSummary(pageSize, offset);
    }

    @Override
    public Post getPostById(Long id) {
        return postMapper.getPostById(id).orElseThrow(() -> new RuntimeException("Post with spescific id does not exist"));
    }

    @Override
    @Transactional
    public void savePost(Post post) {

       String ps= passwordEncoder.encode(post.getPassword());

       post.setPassword(ps);

        postMapper.insert(post);

        if(post.getBodyContent() != null) {
            bodyContentMapper.insert(post.getBodyContent(), post.getId());
        }
    }

    @Override
    public void saveView(Long id) {
        postMapper.getPostById(id).ifPresentOrElse((post)-> {
            viewerMapper.insert(post.getId());
        }, () -> new RuntimeException("Post with spesific id is not exist!") );
    }
}
