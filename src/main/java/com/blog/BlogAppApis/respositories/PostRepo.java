package com.blog.BlogAppApis.respositories;

import com.blog.BlogAppApis.entities.Category;
import com.blog.BlogAppApis.entities.Post;
import com.blog.BlogAppApis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);




}
