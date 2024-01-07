package com.blog.BlogAppApis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//post is a subresource,having 2 parent resources-category and user

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @Column(name = "post_title",length = 100,nullable = false)
    private String title;
    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;


    //we need both of these while adding
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)//when post is removed all its comments will be removed
    private Set<Comment> comments=new HashSet<>();



}
