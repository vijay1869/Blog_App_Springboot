package com.blog.BlogAppApis.respositories;

import com.blog.BlogAppApis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    CommentRepo extends JpaRepository<Comment,Integer> {
}
