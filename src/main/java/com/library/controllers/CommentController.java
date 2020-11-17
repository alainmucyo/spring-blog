package com.library.controllers;

import com.library.models.Comment;
import com.library.models.Post;
import com.library.models.User;
import com.library.repositories.CommentRepository;
import com.library.repositories.PostRepository;
import com.library.repositories.UserRepository;
import com.library.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;

    @PostMapping
    public String save(@Valid Comment comment, Principal principal, @RequestParam int postId) {
        User user = userRepository.findByEmail(principal.getName());

        Post post = postService.show(postId);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
return "redirect:/thisPost?post="+postId;

    }
}
