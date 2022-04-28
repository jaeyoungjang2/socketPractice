package com.sparta.websocketpractice.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class SignupRequestDto {

    private String username;

    private String password;

    private String passwordCheck;

    private String nickname;


}