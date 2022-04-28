package com.sparta.websocketpractice.controller;

// import 생략...

import com.sparta.websocketpractice.domain.User;
import com.sparta.websocketpractice.dto.ChatMessageDto;
import com.sparta.websocketpractice.repository.UserRepository;
import com.sparta.websocketpractice.security.UserDetailsImpl;
import com.sparta.websocketpractice.security.jwt.JwtDecoder;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;
    private final UserRepository userRepository;
    private final JwtDecoder jwtDecoder;

    /**
     * websocket "/pub/chat/enter"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto chatMessageDto, @Header("token") String token) {

        Long userId = chatMessageDto.getUserId();
        User otherUser = userRepository.findById(userId).get();
        chatMessageDto.setUsername(otherUser.getUsername());

//        chatMessageDto.setMessage(String.format("%s님이 %s방에 입장하셨습니다.", username, roomname));
        String topic = channelTopic.getTopic();
        redisTemplate.convertAndSend(topic, chatMessageDto);
    }

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto chatMessageDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String roomname = chatMessageDto.getRoomname();

        String topic = channelTopic.getTopic();
        redisTemplate.convertAndSend(topic, chatMessageDto);
    }
}