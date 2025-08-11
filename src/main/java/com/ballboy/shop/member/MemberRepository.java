package com.ballboy.shop.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByDisplayNameAndPassword(String displayName, String password);

    Optional<Member> findByDisplayName(String displayName);

    Optional<Member> findByUsername(String username);
}
