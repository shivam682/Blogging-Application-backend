package com.shivam.blog.application.paylods;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=4, message="User name must be of minimum of length 4")
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String about;


}
