package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(length = 300)
    private String description;
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL,fetch =FetchType.LAZY)
    private List<Post> posts= new ArrayList<>();
}
