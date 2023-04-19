package com.example.bloglv2.post.entity;

import com.example.bloglv2.global.entity.Timestamped;
import com.example.bloglv2.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private String title;
    private String content;
    private String username;
    private String password;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.username = postRequestDto.getUsername();
        this.password = postRequestDto.getPassword();
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
    }
}
