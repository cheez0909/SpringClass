package controllers.member;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator는 검증 대상이 있음
 * 이 검증 대상은 커맨드 객체
 * 커맨드 객체
 */
@Component // 빈으로 등록되어있어야함
public class JoinValidator implements Validator {

    /**
     * 검증 커맨드 객체를 제한
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    /**
     * 실제로 검증을 수행할 메서드
     *
     * @param target the object that is to be validated / 검증할 커맨드 객체
     * @param errors contextual state about the validation process / 검증 실패시 에러 정보를 처리
     */
    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 필수 항목 검증(id, pw, pwcheck, name, agree)
         * 1. 중복 아이디 불가
         * 2. pw와 pwcheck가 일치해야함
         * 3. 아이디 최소 자리수(6자리 이상) 체크
         * 4. 비밀번호 최소 자리수(8자리 이상) 체크
         * 5. 이메일은 필수값은 아니지만 이메일 형식을 확인
         * 5. reject value
         */

        RequestJoin form = (RequestJoin)target;
        String pw = form.getPw();
        String pwcheck = form.getPwcheck();
        if(StringUtils.hasText(pw)&&StringUtils.hasText(pwcheck)&&!pw.equals(pwcheck)){
            errors.rejectValue("pwcheck", "Mismatch");
        }


//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwcheck", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");



//        String id = form.getId();
//        String pw = form.getPw();
//        String pwcheck = form.getPwcheck();
//        String name = form.getName();
//        boolean agree = form.isAgree();
//
//        /**
//         * if(userId==null || userId.isBlank())
//         */
//        if(!StringUtils.hasText(id)){
//            errors.rejectValue("id", "Required", "아이디를 입력하세요");
//        }
//
//        if(!StringUtils.hasText(pw)){
//            errors.rejectValue("pw", "Required", "비밀번호를 입력하세요");
//        }
//
//        if(!StringUtils.hasText(pwcheck)){
//            errors.rejectValue("pwcheck", "Required", "비밀번호확인을 입력하세요");
//        }
//        if(!StringUtils.hasText(name)){
//            errors.rejectValue("name", "Required", "이름을 입력하세요");
//        }
    }
}
