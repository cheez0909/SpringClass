package common.validator;

import models.member.Member;

public interface RequiredValidator {
    default void checkRequired(String str, RuntimeException e){
        if(str == null || str.isBlank()){
            throw e;
        }
    };
}
