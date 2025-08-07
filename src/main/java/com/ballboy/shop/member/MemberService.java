package com.ballboy.shop.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(Member member) {

        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));

        Member result = memberRepository.save(member);
        return result;
    }
}
