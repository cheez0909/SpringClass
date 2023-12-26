package org.choongang.repository;

import org.choongang.entites.BoardData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData, Long> {
    List<BoardData> findBySubjectContaining(String keyword); // ... where x.Subject like ?1 (parameter bound wrapped in %)

    List<BoardData> findBySubjectContainingOrderBySeqDesc(String keyword);

    // Pageable인터페이스만 쿼리 매개변수에 넣으면 구현을 알아서 해줌
    Page<BoardData> findBySubjectContaining(String keyword, Pageable pageable);
}
