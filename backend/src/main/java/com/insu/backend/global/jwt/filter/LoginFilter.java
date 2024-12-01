package com.insu.backend.global.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insu.backend.global.jwt.dto.LoginRequest;
import com.insu.backend.global.jwt.entity.Refresh;
import com.insu.backend.global.jwt.repository.RefreshRepository;
import com.insu.backend.global.response.ErrorResponse;
import com.insu.backend.global.response.SuccessResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshRepository refreshRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginRequest = new LoginRequest();

        try {
            ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터를 객체로 변환하기 위한 ObjectMapper 객체 생성
            ServletInputStream inputStream = request.getInputStream(); // 요청으로 들어온 데이터를 읽기 위한 InputStream 객체 생성
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // InputStream 객체를 이용하여 요청으로 들어온 데이터를 문자열로 변환
            loginRequest = objectMapper.readValue(messageBody, LoginRequest.class); // 변환된 문자열을 LoginRequest 객체로 변환
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String memberId = loginRequest.getMemberId();
        String memberPwd = loginRequest.getMemberPwd();

        log.info(">>>>> memberId : {}", memberId);
        log.info(">>>>> memberPwd : {}", memberPwd);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberId, memberPwd); // 로그인 요청을 처리하기 위한 토큰 생성

        return authenticationManager.authenticate(token); // 토큰을 이용하여 로그인 요청 처리
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info(">>>>> Login Success member : {}", authResult.getName());

        String memberId = authResult.getName();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt("access", memberId, role, 60*60*1000L); // 1시간 유효
        String refreshToken = jwtUtil.createJwt("refresh", memberId, role, 24*60*60*1000L); // 1일 유효
        addRefreshEntity(memberId, refreshToken, 24*60*60*1000L);

        response.setHeader("Authorization", accessToken);
        response.addCookie(createCookie("Refresh", refreshToken));

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        SuccessResponse successResponse = SuccessResponse.builder()
                        .code(String.valueOf(HttpStatus.OK.value()))
                                .message("로그인에 성공했습니다.")
                                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error(">>>>> Login Fail : {}", failed.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED))
                .message("로그인에 실패했습니다.")
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
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
        cookie.setMaxAge(24*60*60); // 1일

        return cookie;
    }
}
