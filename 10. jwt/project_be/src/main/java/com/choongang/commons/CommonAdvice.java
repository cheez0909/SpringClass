package com.choongang.commons;

import com.choongang.commons.exceptions.CommonException;
import com.choongang.commons.rests.JSONData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice("com.choongang")
public class CommonAdvice {

    private final Utils utils;
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData> errorHandler(Exception e){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        Object message = e.getMessage();

        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();
            Errors errors = commonException.getErrors();
            if(errors != null) {
                Map<String, List<String>> messages = utils.getErrorMessages(errors);
                // 커맨드 객체 검증
                if(messages != null && messages.isEmpty()){
                    message = messages;
                }
            }
        }

        e.printStackTrace();
        JSONData data = new JSONData();
        data.setSuccess(false); // 오류니까 항상 false
        data.setStatus(status);
        data.setMessages(message);

        return ResponseEntity.status(status).body(data); // 상세하게 처리하려고 할 때
    }
}
