package com.example.bloglv2.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String username;
    private String password;

}
