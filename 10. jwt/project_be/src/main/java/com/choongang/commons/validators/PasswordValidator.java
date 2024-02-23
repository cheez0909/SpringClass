package com.choongang.commons.validators;

import java.util.regex.Pattern;

public interface PasswordValidator {

    default boolean alphaCheck(String password, boolean caseInsensitive){

        /* 대소문자 구분없이 체크 */
        if(caseInsensitive) {
            Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
            return pattern.matcher(password).find();
        }
        Pattern pattern1 = Pattern.compile("[a-z]+"); // 소문자 1개 이상
        Pattern pattern2 = Pattern.compile("[A-Z]+"); // 대문자 1개 이상

        return pattern1.matcher(password).find() && pattern2.matcher(password).find();

    }

    /* 숫자체크 */
    default boolean numberCheck(String password){
//        Pattern pattern = Pattern.compile("[0-9]+");
        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(password).find();
    }

    default boolean specialCharsCheck(String password){
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()\\-_+={}]+");
        return pattern.matcher(password).find();
    }
}
