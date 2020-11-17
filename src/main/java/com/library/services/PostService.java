package com.library.services;

import com.library.models.Post;
import com.library.models.User;
import com.library.repositories.CategoryRepository;
import com.library.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;

    public Post show(int id){
        return postRepository.getOne(id);
    }
    public List<Post> getAll(){
        return postRepository.findAll();
    }
    public List<Post> findAllByUser(User user){
        return postRepository.findAllByUser(user);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Post getPost(int id){
        return postRepository.findById(id).get();
    }
}
