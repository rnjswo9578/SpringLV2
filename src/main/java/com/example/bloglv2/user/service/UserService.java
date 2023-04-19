package com.example.bloglv2.user.service;

import com.example.bloglv2.global.dto.ResponseDto;
import com.example.bloglv2.global.jwt.JwtUtil;
import com.example.bloglv2.user.dto.LoginRequestDto;
import com.example.bloglv2.user.dto.SignupRequestDto;
import com.example.bloglv2.user.entity.User;
import com.example.bloglv2.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 아이디 비밀번호 유효성 확인
        // @Pattern 같은 어노테이션으로 가능 => html 을 구현을 안했기 때문에 service 에서 해결
        if (username.length() < 4 || username.length() > 10 || !Pattern.matches("[a-z0-9]*$", username)) {
            return new ResponseDto("아이디를 다시 입력해 주세요!", 100);
        }
        if (password.length() < 8 || password.length() > 15 || !Pattern.matches("[A-Za-z0-9]*$", password)) {
            return new ResponseDto("비밀번호를 다시 입력해 주세요!", 100);
        }

        //
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("해당 유져가 이미 있습니다!");
        }

        User newUser = new User(username, password);
        userRepository.save(newUser);
        return new ResponseDto("회원가입 성공", 200);
    }

    @Transactional(readOnly = true)
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        return new ResponseDto("로그인 성공", 200);
    }
}
