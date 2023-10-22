package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="CATEGORY")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false, length = 100)
    private Integer catId;
    @Column(name="TITLE",nullable = false, length = 100)
    private String title;
    @Column(name="DESCRIPTION",length = 300)
    private String description;
}
