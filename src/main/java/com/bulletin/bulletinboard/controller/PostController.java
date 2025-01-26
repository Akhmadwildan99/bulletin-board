package com.bulletin.bulletinboard.controller;

import com.bulletin.bulletinboard.dto.ApiResponse;
import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostSummary;
import com.bulletin.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public String getPaginatedPosts(@RequestParam(defaultValue = "1", required = false) int page,
                                        @RequestParam(defaultValue = "5", required = false) int size,
                                        Model model) {
        List<PostSummary> posts = postService.getPostsSummary(page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "posts";
    }

    @GetMapping("/posts/detail/{id}")
    public String getPostDetailById(@PathVariable Long id, Model model) {

        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts-detail";

    }


    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {

        try {
            Post post = postService.getPostById(id);
            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.OK.getReasonPhrase(), "Successes to fetch bulletin post", HttpStatus.OK.value());
            ApiResponse<Post> response = new ApiResponse<>(meta, post);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), HttpStatus.BAD_REQUEST.value());
            ApiResponse<String> response = new ApiResponse<>(meta, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            ApiResponse<String> response = new ApiResponse<>(meta, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
