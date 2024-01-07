package com.blog.BlogAppApis.services.Impl;

import com.blog.BlogAppApis.entities.Category;
import com.blog.BlogAppApis.entities.Post;
import com.blog.BlogAppApis.entities.User;
import com.blog.BlogAppApis.exceptions.ResourceNotFoundException;
import com.blog.BlogAppApis.payloads.PostDto;
import com.blog.BlogAppApis.payloads.PostResponse;
import com.blog.BlogAppApis.respositories.CategoryRepo;
import com.blog.BlogAppApis.respositories.PostRepo;
import com.blog.BlogAppApis.respositories.UserRepo;
import com.blog.BlogAppApis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Catgeory","Id",categoryId));


        Post post=this.modelMapper.map(postDto,Post.class);//title,content
        //we need not send the follwoing data as input
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        //
        post.setUser(user);
        post.setCategory(category);
        //

        Post addedPost=this.postRepo.save(post);//at this time post id gets generated automatically

        return this.modelMapper.map(addedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer pid) {
        Post post=this.postRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","id",pid));
        //post=this.modelMapper.map(postDto,Post.class);
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(post.getImageName());
        Post updatedPost=this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer pid) {
        Post post=this.postRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","id",pid));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageSize, Integer pageNumber) {
//        int pageSize=5;-5posts per page
//        int pageNumber=1;-which page we wan to get out of all those pages made out of above size
        //
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Post> posts=this.postRepo.findAll(pageable);
        //all info is present in this posts variable
        //
        List<Post> content = posts.getContent();
        //
        List<PostDto> c;
        c = content.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(c);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer cid) {
        Category category=this.categoryRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("Category","Id",cid));

        List<Post> posts=this.postRepo.findByCategory(category);
        return posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer uid) {

        User user=this.userRepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","Id",uid));

        List<Post> posts=this.postRepo.findByUser(user);
        return posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }



    @Override
    public void deletePost(Integer pid) {
        Post post=this.postRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","id",pid));
        //it is found
        this.postRepo.delete(post);
    }
}
