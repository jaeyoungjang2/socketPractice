package com.sparta.websocketpractice.controller;

// import 생략...

import com.sparta.websocketpractice.domain.ChatRoom;
import com.sparta.websocketpractice.dto.ChatRoomDto;
import com.sparta.websocketpractice.repository.ChatRoomRepository;
import com.sparta.websocketpractice.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅 리스트 화면
    @GetMapping("/")
    public String rooms(Model model) {
        return "rooms";
    }

    @PostMapping("/api/rooms")
    @ResponseBody
    public void createRoom(@RequestBody ChatRoomDto chatRoomDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        chatRoomRepository.createRoom(chatRoomDto, userDetails.getUser());
    }

    @GetMapping("/api/rooms")
    @ResponseBody
    public List<ChatRoom> getAllRoom() {
        return chatRoomRepository.findAllRoom();
    }

    @GetMapping("/rooms/{roomname}")
    public String enter(@PathVariable String roomname, Model model) {
        model.addAttribute("roomname", roomname);
        return "room";
    }
}