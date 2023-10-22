package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="USERS")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="NAME",nullable = false,length = 100)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD" ,nullable =false, length =20)
    private String password;
    @Column(name="ABOUT",length = 300)
    private String about;
}
