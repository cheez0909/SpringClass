package org.choongang.entites;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode // 유일성 보장을 위해서
@AllArgsConstructor // all생성자(멤버 변수를 초기화할 수 있는 생성자와)
@NoArgsConstructor // 기본 생성자(기본생성자가 필요함)
public class BoardViewId {
    private Long seq;
    private int uid;
}
