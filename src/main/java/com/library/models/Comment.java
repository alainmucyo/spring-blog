package com.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Column(length = 1000)
    private String content;
    @CreatedDate
    private Date createdAt=new Date();
     
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
     
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content, Date createdAt, User user, Post post) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.post = post;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
