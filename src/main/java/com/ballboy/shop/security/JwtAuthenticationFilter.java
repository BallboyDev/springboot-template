package com.ballboy.shop.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("ballboy >> void doFilterInternal");
        // 1. Request Header에서 토큰 추출
        String token = jwtUtil.resolveToken(request);

        System.out.println(token.toString());

        // 2. 토큰 유효성 검사
        if (token != null && jwtUtil.validateToken(token)) {
            // 3. 토큰이 유효하면, 토큰으로부터 사용자 정보를 가져와서 SecurityContext에 저장
            Claims claims = jwtUtil.getUserInfoFromToken(token);
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(claims.getSubject())
                    .password("") // 비밀번호는 사용하지 않으므로 비워둠
                    .authorities(claims.get("auth", String.class))
                    .build();

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("test");
        }

        // 4. 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
