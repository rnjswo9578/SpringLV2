package com.example.bloglv2.post.entity;

import com.example.bloglv2.global.entity.Timestamped;
import com.example.bloglv2.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String username;

    public Post(PostRequestDto postRequestDto, Long userId, String username) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.userId = userId;
        this.username = username;
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
    }
}
