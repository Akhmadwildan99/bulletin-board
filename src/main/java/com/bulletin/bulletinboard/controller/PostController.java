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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public String getPaginatedPosts(@RequestParam(defaultValue = "1", required = false) int page,
                                        @RequestParam(defaultValue = "5", required = false) int size,
                                        Model model, @RequestParam(required = false) String successMessage) {
        List<PostSummary> posts = postService.getPostsSummary(page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("successMessage", successMessage);
        return "posts";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post) {
        // Implementasi untuk menyimpan post ke database
        postService.savePost(post);
//        redirectAttributes.addFlashAttribute("successMessage", "Post has been successfully added.");
        return "redirect:/posts?successMessage=Post has been successfully added.";
    }

    @GetMapping("/posts/new")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-create";
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
