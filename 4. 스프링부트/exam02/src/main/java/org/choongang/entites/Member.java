package org.choongang.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {
    @Id
    private Long seq;
    private String email;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
