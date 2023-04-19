package com.example.bloglv2.post.controller;

import com.example.bloglv2.global.dto.ResponseDto;
import com.example.bloglv2.post.dto.PostRequestDto;
import com.example.bloglv2.post.dto.PostResponseDto;
import com.example.bloglv2.post.service.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postService;

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest){
        return postService.createPost(postRequestDto, httpServletRequest);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }


    @GetMapping
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest){
        return postService.updatePost(id, postRequestDto, httpServletRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest httpServletRequest){
        return postService.deletePost(id, postRequestDto, httpServletRequest);
    }

}
