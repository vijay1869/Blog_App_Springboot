package com.blog.BlogAppApis.services;

import com.blog.BlogAppApis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer pid);
    void deleteComment(Integer commentId);

}
