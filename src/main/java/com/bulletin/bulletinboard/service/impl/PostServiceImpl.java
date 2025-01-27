package com.bulletin.bulletinboard.service.impl;

import com.bulletin.bulletinboard.Utils;
import com.bulletin.bulletinboard.mapper.BodyContentMapper;
import com.bulletin.bulletinboard.mapper.PostMapper;
import com.bulletin.bulletinboard.mapper.ViewerMapper;
import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostCredential;
import com.bulletin.bulletinboard.model.PostSummary;
import com.bulletin.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void updatePost(Post post, String keyUpdate) {
        Optional<PostCredential> postCredential = postMapper.getPostCredentialById(post.getId());

        if(!postCredential.isPresent()) {
            throw new RuntimeException("Post with spesific id does not exist!");
        }

        if(keyUpdate == null) {
            throw new RuntimeException("Update Post does not have permission!");
        }

        if(Instant.now().isAfter(postCredential.get().getUpdatedKeyDate().plus(1, ChronoUnit.DAYS))  ) {
            throw new RuntimeException("Update Post key date is expired!");
        }

        if(!postCredential.get().getUpdatedKey().equals(keyUpdate)) {
            throw new RuntimeException("Permission is incorrect!");
        }


        postMapper.update(post);
        if(post.getBodyContent() != null) {
            bodyContentMapper.update(post.getBodyContent(), post.getId());
        }

    }

    @Override
    public String generateKeyUpdatePost(Long id, String password) {
        String key;

        Optional<PostCredential> post = postMapper.getPostCredentialById(id);

        if(!post.isPresent()) {
            throw new RuntimeException("Post with spesific id does not exist");
        }
        if(passwordEncoder.matches(password, post.get().getPassword())) {
                key = Utils.generateRandomChars("ABC", 10);
                post.get().setUpdatedKey(key);
                postMapper.updateKey(post.get());
        } else {
            throw new RuntimeException("Incorrect password");
        }
        return key;
    }


}
