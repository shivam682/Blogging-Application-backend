package com.shivam.blog.application.repositries;

import com.shivam.blog.application.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
