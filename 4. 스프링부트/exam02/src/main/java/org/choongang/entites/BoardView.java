package org.choongang.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

/**
 * 게시글 조회 관련된 클래스
 * 게시글 id와 브라우저 정보(uid)를 가지고
 * uniqe 클래스를 만듦
 */

@Data
@Entity
@IdClass(BoardView.class)
public class BoardView {
    @Id
    private Long seq; // 게시글 번호
    /**
     * id를 묶어서 관리할 수 있는
     * IdClass를 만들어야함
     * 조합하더라도 대표해야함
     * 동등성 비교가 반드시 들어가야함(equals, hashcode)
     */
    @Id
    @Column(name = "_uid")
    private int uid; // 오라클내의 예약어 이기 때문에 오류가 발생할 수 있음
}
