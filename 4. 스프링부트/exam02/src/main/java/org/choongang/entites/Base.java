package org.choongang.entites;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 상속을 통해 공통 속성을 분리하자!
 * 엔티티를 만들지 않고 상속을 통해서만 정의할 수 있도록 사용
 */

/**
 * 공통 속성화를 위한 상위클래스임을 알려주는 애너테이션
 *  @MappedSuperclass
 */
@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {

    @CreatedDate
    @Column(updatable = false) // 추가O, 수정X
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false) // 추가X, 수정O
    private LocalDateTime modifiedAt;
}
