package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content;
    private String imageName;
    @Column(nullable = false)
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(mappedBy = "post",cascade =CascadeType.ALL,fetch =FetchType.LAZY)
    private Set<Comment> comments= new HashSet<>();
}
