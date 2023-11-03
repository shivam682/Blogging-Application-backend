package com.shivam.blog.application.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    private int id;
    private String name;
}
