package com.insu.backend.global.jwt.dto;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String memberId;
    private String memberPwd;
}
