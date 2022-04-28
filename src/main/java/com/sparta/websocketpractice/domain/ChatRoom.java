package com.sparta.websocketpractice.domain;

import com.sparta.websocketpractice.dto.ChatRoomDto;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom implements Serializable {

    private static Long ROOM_SEQUENCE = 1L;

    private Long id;
    private String roomname;
    private String username;

    public ChatRoom(ChatRoomDto chatRoomDto, User user) {
        this.id = ROOM_SEQUENCE ++;
        this.roomname = chatRoomDto.getRoomname();
        this.username = user.getUsername();
    }
}
