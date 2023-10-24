package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.util.Date;

@Entity
@Data
@Table(name="POSTS")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(name="TITLE",nullable = false, length = 100)
    private String title;
    @Column(name="CONTENT",nullable = false, length = 10000)
    private String content;
    @Column(name="IMAGENAME")
    private String imageName;
    @Column(name="ADDEDDATE",nullable = false)
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

}
