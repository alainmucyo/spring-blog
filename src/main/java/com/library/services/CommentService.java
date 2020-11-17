package com.library.services;

import com.library.models.Comment;
import com.library.models.Post;
import com.library.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    public List<Comment> getByPost(Post post){
        return commentRepository.findAllByPostOrderByIdDesc(post);
    }
    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
