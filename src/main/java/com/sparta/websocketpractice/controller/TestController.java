package com.sparta.websocketpractice.controller;

import com.sparta.websocketpractice.domain.User;
import com.sparta.websocketpractice.dto.SignupRequestDto;
import com.sparta.websocketpractice.repository.UserRepository;
import com.sparta.websocketpractice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        User user = new User(requestDto.getUsername(), "임의의 닉네임",
            passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        return "redirect:/user/loginView";
    }

    @GetMapping("/api/user/islogin")
    @ResponseBody
    public String userIsLogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUser().getUsername();
    }
}
