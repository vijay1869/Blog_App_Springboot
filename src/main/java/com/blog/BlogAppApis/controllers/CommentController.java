package com.blog.BlogAppApis.controllers;

import com.blog.BlogAppApis.payloads.ApiResponse;
import com.blog.BlogAppApis.payloads.CommentDto;
import com.blog.BlogAppApis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;
    //uid also required?
    @PostMapping("/post/{pid}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer pid){
        CommentDto createdComment=this.commentService.createComment(commentDto,pid);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted",true),HttpStatus.OK);
    }

}
