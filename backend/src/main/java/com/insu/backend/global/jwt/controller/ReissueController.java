package com.insu.backend.global.jwt.controller;

import com.insu.backend.global.exception.InvalidTokenException;
import com.insu.backend.global.jwt.entity.Refresh;
import com.insu.backend.global.jwt.filter.JWTUtil;
import com.insu.backend.global.jwt.repository.RefreshRepository;
import com.insu.backend.global.response.SuccessResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @PostMapping("/reissue")
    public ResponseEntity<SuccessResponse<Void>> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("Refresh")) {
                refresh = cookie.getValue();
            }
        }

        if(refresh == null) {
            log.error(">>>>> refresh-token이 없습니다.");
            throw new InvalidTokenException();
        }

        if(jwtUtil.isExpired(refresh)) {
            log.error(">>>>> refresh-token이 만료되었습니다.");
            refreshRepository.deleteByRefresh(refresh);
            throw new InvalidTokenException();
        }

        log.info(jwtUtil.getCategory(refresh));

        if(!jwtUtil.getCategory(refresh).equals("refresh")) {
            log.error(">>>>> refresh-token이 아닙니다.");
            throw new InvalidTokenException();
        }

        if(!refreshRepository.existsByRefresh(refresh)) {
            log.error(">>>>> 존재하지 않는 refresh-token입니다.");
            throw new InvalidTokenException();
        }

        log.info(">>>>> valid refresh token");

        String memberId = jwtUtil.getMemberId(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccessToken = jwtUtil.createJwt("access", memberId, role, 60*60*1000L);
        String newRefreshToken = jwtUtil.createJwt("refresh", memberId, role, 24*60*60*1000L);

        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(memberId, newRefreshToken, 24*60*60*1000L);

        response.setHeader("Authorization", newAccessToken);
        response.addCookie(createCookie("Refresh", newRefreshToken));

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("토큰 재발급 성공")
                .build());
    }

    private void addRefreshEntity(String memberId, String refreshToken, Long expiredMs) {
        Refresh refresh = Refresh.builder()
                .memberId(memberId)
                .refresh(refreshToken)
                .expiration(String.valueOf(new Date(System.currentTimeMillis() + expiredMs)))
                .build();

        refreshRepository.save(refresh);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60);

        return cookie;
    }
}
