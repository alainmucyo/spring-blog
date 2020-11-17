package com.library.controllers;

import com.library.models.Post;
import com.library.models.User;
import com.library.repositories.CategoryRepository;
import com.library.repositories.PostRepository;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Principal principal) {

        return principal == null ? "auth/login" : "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String store(@Valid User user, BindingResult bindingResult, Model model) {
        if (userService.userExists(user.getEmail())) {
            model.addAttribute("exists", true);
            return "auth/register";
        }
        if (bindingResult.hasErrors())
            return "auth/register";
        userService.store(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = " ") String search,
                       @RequestParam(defaultValue = "0") int category,
                       @RequestParam(defaultValue = " ") String year
    ) {

        Pageable pageable = PageRequest.of(page - 1, 4);

        if (search.trim().isEmpty() && category == 0 && year.trim().isEmpty())
            model.addAttribute("posts", postRepository.findAll(pageable));
        if (!search.trim().isEmpty())
            model.addAttribute("posts", postRepository.findAllByContentLike("%" + search + "%"));
        if (!year.trim().isEmpty())
            model.addAttribute("posts", postRepository.findAllByCreated_atLike(year));
        if (category != 0)
            model.addAttribute("posts", postRepository.findAllByCategory(categoryRepository.getOne(category)));

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("search", search);
        model.addAttribute("year", year);
        model.addAttribute("currentPage", page);
        model.addAttribute("paginate", search.trim().isEmpty() && category == 0 && year.trim().isEmpty());
        model.addAttribute("tag", category);
        model.addAttribute("archives", postRepository.findDateCreatedAt());


        return "index";
    }


}
