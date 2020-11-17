package com.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Column(length = 2000)
    private String content;

     
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private Date created_at = new Date();

     
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
     
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post(String name,String content, User user, Date created_at, Category category, List<Comment> comments) {
        this.name = name;
        this.content = content;
        this.user = user;
        this.created_at = created_at;
        this.category = category;
        this.comments = comments;
    }

    public Post(String name, String content, Date created_at) {
        this.name = name;
        this.content = content;
        this.created_at = created_at;
    }

    public Post(@NotEmpty String name, @NotEmpty String content, Date created_at, Category category) {
        this.name = name;
        this.content = content;
        this.created_at = created_at;
        this.category = category;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", created_at=" + created_at +
                ", category=" + category +
                ", comments=" + comments +
                '}';
    }
}
