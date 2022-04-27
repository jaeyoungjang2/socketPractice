package com.sparta.websocketpractice.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto implements Serializable {

    // 상대방 이름
    private String roomname;
    // 채팅 신청한 사람
    private String username;
}