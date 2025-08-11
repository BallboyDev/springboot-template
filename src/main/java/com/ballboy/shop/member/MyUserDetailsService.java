package com.ballboy.shop.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ballboy.shop.security.JwtUtil;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MyUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 로그인 기능
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername");

        // DB에서 username을 가진 유저를 찾아온다.
        Optional<Member> result = memberRepository.findByUsername(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("아이디 없음");
        }
        Member user = result.get();

        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        auth.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUsername(), user.getPassword(), auth);

    }
}
