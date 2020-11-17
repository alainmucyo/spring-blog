package com.library.repositories;

import com.library.models.Category;
import com.library.models.Post;
import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM post WHERE content LIKE ?1 ORDER BY created_at DESC ",nativeQuery = true)
    List<Post> findAllByContentLike(String name);

    @Query(value = "SELECT * FROM post  ORDER BY created_at DESC "
            ,countQuery = "SELECT COUNT(*) FROM post"
            ,nativeQuery = true)
    List<Post> findAll();

    List<Post> findAllByCategory(Category category);

    @Query(value = "SELECT * FROM post WHERE YEAR(created_at) = ?1", nativeQuery = true)
    List<Post> findAllByCreated_atLike(String year);

    @Query(value = "SELECT DISTINCT year(created_at) FROM post ORDER BY created_at DESC ", nativeQuery = true)
    List<Date> findDateCreatedAt();

    List<Post> findAllByUser(User user);
}
