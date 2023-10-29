package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    @ManyToOne
    @JoinColumn(name= "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}
