package com.library.controllers;

import com.library.models.Category;
import com.library.models.Comment;
import com.library.models.Post;
import com.library.models.User;
import com.library.repositories.CategoryRepository;
import com.library.repositories.PostRepository;
import com.library.repositories.UserRepository;
import com.library.services.CommentService;
import com.library.services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post")
    public String index(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("post", new Post());

        return "posts/create";
    }

    @PostMapping("/post")
    public String store(@Valid Post post, BindingResult bindingResult, @RequestParam int category, Principal principal) {
        if (bindingResult.hasErrors())
            return "posts/create";

        Category cat = categoryRepository.getOne(category);
        post.setCategory(cat);
        User user = userRepository.findByEmail(principal.getName());
        post.setUser(user);
        postService.save(post);
        return "redirect:/home";
    }

    @GetMapping("/thisPost")
    public String show(@RequestParam int post, Model model) {
        Post myPost = postService.show(post);
        model.addAttribute("comment", new Comment());
        model.addAttribute("post", myPost);
        model.addAttribute("comments", commentService.getByPost(myPost));
        return "posts/show";
    }

    @GetMapping("/myPost")
    public String myPosts(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("posts", postService.findAllByUser(user));
        model.addAttribute("categories", categoryRepository.findAll());
        return "posts/myPost";
    }

    @PostMapping("/save")
    public String save(Post post, @RequestParam int categoryId, Principal principal) {
        Category cat = categoryRepository.getOne(categoryId);
        post.setCategory(cat);
        User user = userRepository.findByEmail(principal.getName());
        post.setUser(user);
        postService.save(post);
        return "redirect:/myPost";
    }

    @GetMapping("/delete/post/{id}")
    public String delete(@PathVariable int id) {
        postRepository.deleteById(id);
        return "redirect:/myPost";
    }
}
