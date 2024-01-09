package com.blog.BlogAppApis.payloads;


import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;//username
    private String password;
}
