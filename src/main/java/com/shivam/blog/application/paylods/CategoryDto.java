package com.shivam.blog.application.paylods;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String id;
    @NotEmpty
    @Size(min= 4, message = "title should be of length minimum of 4")
    private String title;

    @NotEmpty
    @Size(min= 10, max= 300,message = "description should of minimum length of 10 and max length of 300")
    private String description;
}
