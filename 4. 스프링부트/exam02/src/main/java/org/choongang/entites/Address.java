package org.choongang.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

/**
 * 한명의 회원이 한개의 주소를 갖고있다
 */
@Data
@Entity
public class Address {
    @Id @GeneratedValue
    private Long seq; // 기본키는 래퍼클래스

    @Column(length = 80, nullable = false)
    private String addr1;

    @Column(length = 80)
    private String addr2;

    @Column(length = 10, nullable = false)
    private String zipcode;

    // 조회만 가능한 형태
    @ToString.Exclude
    @OneToOne(mappedBy = "address", fetch=FetchType.EAGER)
    private Member member;
}
