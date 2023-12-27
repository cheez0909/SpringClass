package org.choongang.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.choongang.commons.MemberType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
//@Table(name = "USERS",
//        indexes = @Index(name = "idx_member_createdAt", columnList = "createdAt Desc"))
@EntityListeners(AuditingEntityListener.class) //@LastModifiedDate, @CreatedDate를 위해 설정
public class Member extends Base{
    @Id @GeneratedValue
    private Long seq;

    @Column(length = 80, unique = true, nullable = false)
    private String email;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 65, nullable = false)
    private String password;

    /**
     * 공통 속성화 시킨 부분
     */
    // @CreationTimestamp // sql insert실행 시
    // @CreatedDate // 엔티티 생성시, 표준
    // private LocalDateTime createdAt;

    // @UpdateTimestamp // sql update문 실행시
    // @LastModifiedDate // 엔티티 생성시, 표준
    // private LocalDateTime modifiedAt;

    // @Lob // CLOB
//    @Transient // 엔티티 내부에서만 사용
//    private String introduction;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL : 상수의 위치 번호
    @Column(length = 10)
    private MemberType memberType;

    // @Temporal(TemporalType.DATE) // DATE, TIME, TIMESTAMP
    // public Date dt;


    // 하나의 회원이 여러개의 게시글
    // boardData쪽에 있는 멤버
    @ToString.Exclude
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<BoardData> boardData = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addrNo") // 외래키의 필드명으로 추가됨
    private Address address;
}
