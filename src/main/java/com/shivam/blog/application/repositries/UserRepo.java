package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
