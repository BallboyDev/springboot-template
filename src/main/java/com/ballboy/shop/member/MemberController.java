package com.ballboy.shop.member;

import org.springframework.web.bind.annotation.RestController;
import com.ballboy.shop.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
// @CrossOrigin(origins = "*")
public class MemberController {

    private final JwtUtil jwtUtil;

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    public MemberController(MemberService memberService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/join")
    public Member join(@RequestBody Member member) {

        Member result = memberService.join(member);

        System.out.println(member.toString());

        return result;
    }

    // Spring security 내부 프로세스로 대체
    // @PostMapping("/login")
    // public String login(@RequestBody Member member) {
    // String token = memberService.login(member.getDisplayName(),
    // member.getPassword());

    // return token;
    // }

    // 표준 방식을 사용하는 로그인 API
    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        System.out.println("ballboy >> String login");

        // AuthenticationManager에게 인증 위임
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword()));

        // 인증 성공 시, UserDetail 정보를 기반으로 JWT 토큰 생성
        // auth.getname()은 인증된 사용자의 username을 반환 (MyUserDetailService에서 설정)
        String username = auth.getName();

        // DB에서 약할을 조회하거나, 기본 역할을 부여할 수 있습니다.
        return jwtUtil.createToken(username, "ROLE_USER");
    }

}
