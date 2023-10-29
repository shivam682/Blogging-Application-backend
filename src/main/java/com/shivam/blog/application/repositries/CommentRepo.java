package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.Comment;
import com.shivam.blog.application.models.Post;
import com.shivam.blog.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);
    List<Comment> findByPost(Post post);
}
