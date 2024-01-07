package com.blog.BlogAppApis.controllers;

import com.blog.BlogAppApis.entities.Post;
import com.blog.BlogAppApis.payloads.ApiResponse;
import com.blog.BlogAppApis.payloads.PostDto;
import com.blog.BlogAppApis.payloads.PostResponse;
import com.blog.BlogAppApis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("/user/{uid}/category/{cid}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer uid,@PathVariable Integer cid){
        PostDto createPostDto=this.postService.createPost(postDto,uid,cid);
        return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
        //what is returned is a dto,does it has info about category and user?-not in resposnse
    }


    @PutMapping("/posts/{pid}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer pid,@RequestBody PostDto postDto){
        PostDto updatePostDto=this.postService.updatePost(postDto,pid);
        return new ResponseEntity<>(updatePostDto, HttpStatus.OK);
    }

    @GetMapping("/user/{uid}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer uid){
        List<PostDto> postDtos=this.postService.getPostsByUser(uid);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/category/{cid}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer cid){
        List<PostDto> postDtos=this.postService.getPostsByCategory(cid);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts/{pid}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer pid){
        PostDto postDto=this.postService.getPostById(pid);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                    @RequestParam(value = "pageNumber",defaultValue = "0")Integer pageNumber){
        PostResponse postResponse=this.postService.getAllPost(pageSize,pageNumber);
        //pageNumbers is 0-indexed
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{pid}")
    public ApiResponse deletePost(@PathVariable Integer pid){
        this.postService.deletePost(pid);
        return new ApiResponse("Post is succesfully deleted",true);
    }




}
