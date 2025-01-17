package com.insu.backend.global.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insu.backend.global.jwt.dto.CustomUserDetails;
import com.insu.backend.global.response.ErrorResponse;
import com.insu.backend.member.entity.Member;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");
        log.info(">>>>> accessToken : {}", accessToken);
        if(accessToken == null || accessToken.isEmpty()) {
            log.info(">>>>> accessToken is null");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            log.info(">>>>> accessToken is expired");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401코드 반환

            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code("401")
                    .message("토큰 만료되었습니다. 다시 로그인 해주세요.")
                    .build();

            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
            return;
        }

        log.info(">>>>> accessToken is valid");

        String memberId = jwtUtil.getMemberId(accessToken);
        String role = jwtUtil.getRole(accessToken);

        log.info(">>>>> memberId : {}", memberId);
        log.info(">>>>> role : {}", role);

        Member member = Member.builder()
                .memberId(memberId)
                .role(role)
                .build();

        CustomUserDetails userDetails = new CustomUserDetails(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
