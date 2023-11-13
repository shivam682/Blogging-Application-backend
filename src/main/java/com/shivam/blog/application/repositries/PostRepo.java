package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.models.Post;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.paylods.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

//    @Query("SELECT  p from Post p where p.title like : key")
//    List<Post>searchByTitle(@Param("key") String title);


    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
