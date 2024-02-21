package com.choongang.commons.entites;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass // 공통부분을 상속 & 별도의 테이블을 가지지 않음
@EntityListeners(AuditingEntityListener.class) // 이벤트를 감지
public abstract class Base {

    @CreatedDate
    @Column(length = 80, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(length = 80, insertable = false)
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}
