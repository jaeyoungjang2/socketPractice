package com.sparta.websocketpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketApiTest {

    @GetMapping("/api/socket/api/test")
    public void socketApiTest() {
        System.out.println("socket에서 api를 호출하였습니다.");
    }
}
