package org.choongang.repositories;

import org.choongang.entites.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    // userId로 조회화는 메서드
    Member findByUserId(String userId);

    /**
     * 메서드 이름이 길어지면 사용을 권장하지 않음
     */
    List<Member> findByUserNameContaining(String keyword);
    List<Member> findByUserNameContainingOrderByRegDtDesc(String keyword);
    List<Member> findByUserNameContainingOrUserIdContainingOrderByRegDtDesc(String keyword, String keyword2);

    /**
     * 쿼리 메서드
     */
    @Query("SELECT * FROM MEMBER WHERE USER_NAME LIKE '%:key1%' OR USER_ID LIKE '%:key2%' ORDER BY REG_DT DESC")
    List<Member> getMembers(@Param(value = "key1") String keyword, @Param(value = "key2") String keyword2);


    /**
     * 페이징
     */
    Page<Member> findByUserNameContaining(String keyword, Pageable pageable);
}
