package com.sparta.websocketpractice.controller;

// import 생략...

import com.sparta.redistest.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    /**
     * websocket "/pub/chat/enter"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto chatMessageDto) {
        String username = chatMessageDto.getUsername();
        String roomname = chatMessageDto.getRoomname();

        System.out.println("chatMessageDto.getType() = " + chatMessageDto.getType());
        System.out.println("username = " + username);
        System.out.println("roomname = " + roomname);
        System.out.println("입장하셨습니다.");

        chatMessageDto.setMessage(String.format("%s님이 %s방에 입장하셨습니다.", username, roomname));
        String topic = channelTopic.getTopic();
        redisTemplate.convertAndSend(topic, chatMessageDto);
    }

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto chatMessageDto) {
        String username = chatMessageDto.getUsername();
        String roomname = chatMessageDto.getRoomname();

        String topic = channelTopic.getTopic();
        redisTemplate.convertAndSend(topic, chatMessageDto);
    }
}