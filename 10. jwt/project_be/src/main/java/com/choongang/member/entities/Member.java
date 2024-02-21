package com.choongang.member.entities;

import com.choongang.commons.entites.Base;
import com.choongang.member.constants.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends Base {
    @Id @GeneratedValue
    private Long seq;

    @Column(length = 80, unique = true, nullable = false)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String name;

    private boolean enable; // 탈퇴여부
    private boolean locked; // 잠김여부

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Authority authority = Authority.USER;

}
