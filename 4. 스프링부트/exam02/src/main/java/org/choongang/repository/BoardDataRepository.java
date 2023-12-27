package org.choongang.repository;

import org.choongang.entites.BoardData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData, Long>, QuerydslPredicateExecutor<BoardData> {
    @EntityGraph(attributePaths = {"member"})
    List<BoardData> findBySubjectContaining(String keyword); // ... where x.Subject like ?1 (parameter bound wrapped in %)

    List<BoardData> findBySubjectContainingOrderBySeqDesc(String keyword);

    // Pageable인터페이스만 쿼리 매개변수에 넣으면 구현을 알아서 해줌
    Page<BoardData> findBySubjectContaining(String keyword, Pageable pageable);

    @Query("SELECT b FROM BoardData b LEFT JOIN FETCH b.member WHERE b.subject LIKE %:key% ORDER BY b.seq DESC")
    List<BoardData> getSubjects(@Param("key") String keyword);

    // 클래스명과 동일해야함, 대소문자 구분O
    // 실행과정중에만 오류를 알 수 있음, 문법적 오류가 많음
    // 쿼리를 직접 입력하는 것은 좋지 않음
    @Query("SELECT b FROM BoardData b WHERE b.subject LIKE %:key%")
    Page<BoardData> getSubjects(@Param("key") String keyword, Pageable pageable);
}
