package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
