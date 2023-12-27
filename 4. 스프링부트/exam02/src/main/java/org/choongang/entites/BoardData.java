package org.choongang.entites;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BoardData extends Base {
    @Id @GeneratedValue
    private Long seq;

    @Column(length = 100, nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    // 외래키가 만들어짐
    // 많은 쪽에서 외래키가 생성됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo") // 이 이름으로 외래키가 됨 해당필드의 기본키가 외래키가됨
    private Member member;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<HashTag> tags=new ArrayList<>();
}
