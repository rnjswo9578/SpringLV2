package com.example.bloglv2.post.controller;

import com.example.bloglv2.global.dto.IsSuccessDto;
import com.example.bloglv2.post.dto.PostRequestDto;
import com.example.bloglv2.post.dto.PostResponseDto;
import com.example.bloglv2.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postService;


    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
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
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(postRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public IsSuccessDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.deletePost(id, postRequestDto);
    }

}