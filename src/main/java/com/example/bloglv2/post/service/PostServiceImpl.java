package com.example.bloglv2.post.service;

import com.example.bloglv2.global.dto.ResponseDto;
import com.example.bloglv2.global.jwt.JwtUtil;
import com.example.bloglv2.post.dto.PostRequestDto;
import com.example.bloglv2.post.dto.PostResponseDto;
import com.example.bloglv2.post.entity.Post;
import com.example.bloglv2.post.repository.PostRepository;
import com.example.bloglv2.user.entity.User;
import com.example.bloglv2.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl{

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest httpServletRequest) {

        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = checkUser(claims);

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = new Post(postRequestDto, user.getId(), user.getUsername());
            Long id = postRepository.saveAndFlush(post).getId();

            return new PostResponseDto(checkPost(id));
        } else {
            return null;
        }
    }

    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(checkPost(id));
    }

    public List<PostResponseDto> getPostList() {
        //findAll()은 List<Post> 형으로 반환 ->stream을 통해 매핑 -> new PostResponseDto로 리턴타입 맞추기
        return postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest httpServletRequest) {

        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = checkUser(claims);
            Post post = checkPost(id);

            if (post.getUsername().equals(user.getUsername())) {
                post.update(postRequestDto);
            }

            return new PostResponseDto(checkPost(id));
        } else {
            return null;
        }
    }

    public ResponseDto deletePost(Long id, HttpServletRequest httpServletRequest) {

        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = checkUser(claims);
            Post post = checkPost(id);

            if (post.getUsername().equals(user.getUsername())) {
                postRepository.delete(post);
            }

            return new ResponseDto("삭제 성공", 200);
        } else {
            return new ResponseDto("삭제 실패",100);
        }
    }

    private Post checkPost(Long id){
        return  postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 포스트가 없습니다!!!")
        );
    }
    private User checkUser(Claims claims) {
        return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다!!!")
        );
    }
}
