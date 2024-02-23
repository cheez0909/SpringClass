package com.choongang.member.repositories;

import com.choongang.member.entities.Member;
import com.choongang.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);

    default boolean exists(String email){
        QMember member = QMember.member;

        return exists(member.email.eq(email));
    }

}
