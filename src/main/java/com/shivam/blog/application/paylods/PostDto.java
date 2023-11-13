package com.shivam.blog.application.paylods;

import com.shivam.blog.application.models.Category;
import com.shivam.blog.application.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imageName;

    private Date addedDate;

    @NotEmpty
    private CategoryDto category;

    @NotEmpty
    private UserDto user;


}
