package com.sparta.websocketpractice.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto implements Serializable {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type; // 메시지 타입
    private String roomname; // 방 이름
    private String username; // 메시지 보낸사람
    private String message; // 메시지
}