package common.validator;

import models.member.Member;
import org.springframework.util.StringUtils;

public interface RequiredValidator {

    // 널값과 비어있는지 체크
    default void checkRequired(String str, RuntimeException e){
        if(!StringUtils.hasText(str)){
            throw e;
        }
    };

    // 비밀번호 확인여부
    default void checkTrue(boolean res, RuntimeException e){
        if(!res){
            throw e;
        }
    }
    
    // 아이디가 이미 등록되어있는지 체크
    default void checkFalse(boolean res, RuntimeException e){
        if(res){
            throw e;
        }
    }

}
