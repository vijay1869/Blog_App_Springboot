package com.blog.BlogAppApis.payloads;

import com.blog.BlogAppApis.entities.Category;
import com.blog.BlogAppApis.entities.Comment;
import com.blog.BlogAppApis.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer pid;//if u give the same name as in entity then it gets fetched othwewsie no,we get null

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;


    //we need both of these while adding
    private CategoryDto category;//why are they null while returning?
    private UserDto user;//if u give the same name as in entity then it gets fetched othwewsie no,we get null

    private Set<CommentDto> comments=new HashSet<>();//commentDto instead of comment is better
    //otherwise loop
}
