package com.shivam.blog.application.paylods;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {


    private String username;
    private String password;
}
