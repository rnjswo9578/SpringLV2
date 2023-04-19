package com.example.bloglv2.post.service;

import com.example.bloglv2.global.dto.IsSuccessDto;
import com.example.bloglv2.post.dto.PostRequestDto;
import com.example.bloglv2.post.dto.PostResponseDto;
import com.example.bloglv2.post.entity.Post;
import com.example.bloglv2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl{

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        Long id = postRepository.save(post).getId();
        return new PostResponseDto(checkPost(id));
    }

    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(checkPost(id));
    }

    public List<PostResponseDto> getPostList() {
        //findAll()은 List<Post> 형으로 반환 ->stream을 통해 매핑 -> new PostResponseDto로 리턴타입 맞추기
        return postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(PostRequestDto postRequestDto, Long id) {
        Post post = checkPost(id);
        if (postRequestDto.getPassword().equals(post.getPassword())) post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    public IsSuccessDto deletePost(Long id, PostRequestDto postRequestDto) {
        Post post = checkPost(id);
        if (postRequestDto.getPassword().equals(post.getPassword())) {
            postRepository.delete(post);
            return new IsSuccessDto(true);
        }
        return new IsSuccessDto(false);
    }

    private Post checkPost(Long id){
        return  postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 포스트가 없습니다.!!!")
        );
    }
}
