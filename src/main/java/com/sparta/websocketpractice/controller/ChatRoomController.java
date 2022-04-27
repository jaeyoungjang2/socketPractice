package com.sparta.websocketpractice.controller;

// import 생략...

import com.sparta.redistest.domain.ChatRoom;
import com.sparta.redistest.dto.ChatRoomDto;
import com.sparta.redistest.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/rooms")
    public String rooms(Model model) {
        return "rooms";
    }

    @PostMapping("/api/rooms")
    @ResponseBody
    public void createRoom(@RequestBody ChatRoomDto chatRoom) {
        chatRoomRepository.createRoom(chatRoom);
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