package com.blog.BlogAppApis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    //which attributes need to be set while creating a comment?
}
