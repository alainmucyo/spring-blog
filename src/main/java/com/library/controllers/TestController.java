package com.library.controllers;

import com.library.models.Post;
import com.library.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private PostService postService;

    @RequestMapping("/findOne")
    public Post findOne(@RequestParam int postId) {

            Post post=postService.getPost(postId);

       return post;

    }
}
