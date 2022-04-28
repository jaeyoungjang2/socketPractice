package com.sparta.websocketpractice.repository;


import com.sparta.websocketpractice.domain.ChatRoom;
import com.sparta.websocketpractice.domain.User;
import com.sparta.websocketpractice.dto.ChatRoomDto;
import java.util.List;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomRepository {

    private static final String CHAT_ROOMS = "CHAT_ROOM"; // 채팅룸 저장

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, ChatRoom> hashOpsChatRoom;

    // 채팅방 생성
    public ChatRoom createRoom(ChatRoomDto chatRoomDto, User user) {

        ChatRoom chatRoom = new ChatRoom(chatRoomDto, user);
        hashOpsChatRoom.put(CHAT_ROOMS, chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    // 모든 채팅방 조회
    public List<ChatRoom> findAllRoom() {
        return hashOpsChatRoom.values(CHAT_ROOMS);
    }

    // 특정 채팅방 조회
    public ChatRoom findRoomById(String id) {
        return hashOpsChatRoom.get(CHAT_ROOMS, id);
    }
}