package org.choongang.entites;

import jakarta.persistence.*;
import lombok.Data;

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

}
