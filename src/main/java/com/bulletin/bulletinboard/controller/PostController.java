package com.bulletin.bulletinboard.controller;

import com.bulletin.bulletinboard.dto.ApiResponse;
import com.bulletin.bulletinboard.model.Post;
import com.bulletin.bulletinboard.model.PostCredential;
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
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        // Implementasi untuk menyimpan post ke database
       try {

        postService.savePost(post);
    } catch (Exception e) {
        redirectAttributes.addAttribute("message", e.getMessage());
        redirectAttributes.addAttribute("title", "Error!");
        return "redirect:error";
    }
        return "redirect:/posts?successMessage=Post has been successfully added.";
    }

    @GetMapping("/posts/new")
    public String showPostForm(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("post", new Post());
            return "post-create";
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
            redirectAttributes.addAttribute("title", "Error!");
            return "redirect:error";
        }

    }

    @GetMapping("/posts/update/{id}")
    public String showPostFormUpdate(@PathVariable long id, @RequestParam(required = true) String updateKey, Model model) {

        try {
            model.addAttribute("post", postService.getPostById(id));
            model.addAttribute("updateKey", updateKey);
            return "post-create";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("title", "Error!");
            return "redirect:error";
        }
    }

    @GetMapping("/posts/detail/{id}")
    public String getPostDetailById(@PathVariable Long id, Model model) {

        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            PostCredential postCredential = new PostCredential();
            postCredential.setId(post.getId());
            model.addAttribute("postCredential",postCredential);
            return "posts-detail";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("title", "Error!");
            return "redirect:error";
        }


    }

    @GetMapping("/posts/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        try {
            postService.saveView(id);
            return "redirect:/posts/detail/" + id;
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("title", "Error!");
            return "redirect:error";
        }
    }

    @PostMapping("/posts/validate-update/{id}")
    public String validateUpdate(@PathVariable Long id, @ModelAttribute PostCredential postCredential, RedirectAttributes redirectAttributes) {

        try {
            String keyUpdatePost = postService.generateKeyUpdatePost(id, postCredential.getPassword());



            return "redirect:/posts/update/"+ id+"?updateKey="+keyUpdatePost;
        }catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
            redirectAttributes.addAttribute("title", "Error!");
            return "redirect:error";
        }
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, @RequestParam String updateKey,  RedirectAttributes redirectAttributes) {
        try {
//            post.setId(id);
            postService.updatePost(post, updateKey);

            return "redirect:/posts?successMessage=Post with id "+ id +" has been successfully updated.";
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
            redirectAttributes.addAttribute("title", "Error!");
            return "redirect:error";
        }
    }

    @PostMapping("/posts/soft-delete/{id}")
    public String deletePost(@PathVariable Long id, @ModelAttribute PostCredential postCredential,RedirectAttributes redirectAttributes) {
        try {
            postService.softDeletePost(id, postCredential.getPassword());

            return "redirect:/posts?successMessage=Post with id "+ id +" has been successfully deleted.";
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
            redirectAttributes.addAttribute("title", "Error!");
            return "redirect:error";
        }
    }

//    @GetMapping("/posts/{id}")
//    public ResponseEntity<?> getPostById(@PathVariable Long id) {
//
//        try {
//            Post post = postService.getPostById(id);
//            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.OK.getReasonPhrase(), "Successes to fetch bulletin post", HttpStatus.OK.value());
//            ApiResponse<Post> response = new ApiResponse<>(meta, post);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), HttpStatus.BAD_REQUEST.value());
//            ApiResponse<String> response = new ApiResponse<>(meta, null);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            ApiResponse.Meta meta = new ApiResponse.Meta(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//            ApiResponse<String> response = new ApiResponse<>(meta, null);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
