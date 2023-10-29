package com.shivam.blog.application.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(nullable = false,length = 100)
    private String name;
    @Email
    private String email;
    @Column(nullable =false, length =20)
    private String password;
    @Column(length = 300)
    private String about;
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL,fetch =FetchType.LAZY)
    private List<Post> posts= new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL,fetch =FetchType.LAZY)
    private Set<Comment> comments;
}
