package com.blog.BlogAppApis.services.Impl;

import com.blog.BlogAppApis.entities.Comment;
import com.blog.BlogAppApis.entities.Post;
import com.blog.BlogAppApis.exceptions.ResourceNotFoundException;
import com.blog.BlogAppApis.payloads.CommentDto;
import com.blog.BlogAppApis.respositories.CommentRepo;
import com.blog.BlogAppApis.respositories.PostRepo;
import com.blog.BlogAppApis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer pid) {//we need uid also
        Post post=this.postRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","Id",pid));
        //post found
        //we need to add this comment to that post?
        //we need not save this comment separately?
        //which attributes we need to set?,which way?
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);//simple
        comment=this.commentRepo.save(comment);
        //add this comment to the post and save the post?,this is a very hard task -huge process-not scalable

        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        //comment found
        this.commentRepo.delete(comment);//shall we do this by comment id
        //what impact this will have
    }
}
