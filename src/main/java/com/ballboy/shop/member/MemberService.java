package com.ballboy.shop.member;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ballboy.shop.security.JwtUtil;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    // private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        // this.jwtUtil = jwtUtil;
    }

    public Member join(Member member) {

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        Member result = memberRepository.save(member);
        return result;
    }

    // Spring security 내부 프로세스로 대체
    // public String login(String displayName, String password) {

    // Optional<Member> result = memberRepository.findByDisplayName(displayName);

    // if (!passwordEncoder.matches(password, result.get().getPassword())) {
    // throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    // } else {
    // System.out.println("동일한 비밀번호 입니다.");
    // }

    // return jwtUtil.createToken(result.get().getDisplayName());

    // }
}
