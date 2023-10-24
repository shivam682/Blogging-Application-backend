package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.models.Post;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
