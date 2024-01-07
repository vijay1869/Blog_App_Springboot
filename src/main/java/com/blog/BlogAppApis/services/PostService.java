package com.blog.BlogAppApis.services;

import com.blog.BlogAppApis.entities.Post;
import com.blog.BlogAppApis.payloads.PostDto;
import com.blog.BlogAppApis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //crud
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    //when creating it, we neeed both the parent resoucres
    PostDto updatePost(PostDto postDto,Integer pid);
    void deletePost(Integer pid);
    PostDto getPostById(Integer pid);
    PostResponse getAllPost(Integer pageSize, Integer pageNumber);

    List<PostDto> getPostsByCategory(Integer cid);

    List<PostDto> getPostsByUser(Integer uid);



    List<PostDto> searchPosts(String keyword);




}
