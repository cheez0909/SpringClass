package com.choongang.commons.rests;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor // 기본 생성자
@RequiredArgsConstructor
public class JSONData {

    private boolean success = true;
    private HttpStatus status = HttpStatus.OK;

    private Object messages; // 실패시엔 메세지

    @Nonnull
    private Object data; // 성공시엔 데이터
}
