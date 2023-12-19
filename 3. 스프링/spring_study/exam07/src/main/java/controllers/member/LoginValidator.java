package controllers.member;

import lombok.RequiredArgsConstructor;
import models.member.JoinService;
import models.member.Member;
import models.member.MemberDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class LoginValidator implements Validator {

    private final MemberDao memberDao;


    /**
     * 검증하고자 하는 커맨드 객체
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestLogin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 1. 아이디로 회원이 조회 되는지?
         * 2. 조회된 회원의 비밀번호와 입력한 비밀번호의 일치 여부
         */
        RequestLogin form = (RequestLogin) target;

        String userId = form.userId();
        String userPw = form.userPw();

        Member member = memberDao.get(userId);
        if(member == null){
            errors.reject("Login.auth.fail"); // 필드를 정하지 않는 것이 좋음...!
        }

        // 비밀번호가 일치하지 않습니다. -> 아이디는 맞다!
        // !BCrypt.checkpw 비밀번호 검증하는 메서드
        if(member != null && StringUtils.hasText(userPw)
                && !BCrypt.checkpw(userPw, member.getUserPw())){
            errors.reject("Login.auth.fail");
        }

        /**
         * reject메서드
         * reject` 메서드는 대상 객체 전체를 거부(reject)하는 데 사용됩니다.
         *     - 전체 객체에 대한 유효성 검사를 수행할 때 사용하며,
         *     여러 필드 간의 유효성 검사 또는 전체 객체의 상태에 따라
         *     유효성을 결정할 때 유용
         */

        /**
         *  - `rejectValue` 메서드는 대상 객체 내의 특정 필드를
         *  거부하는 데 사용됩니다.
         *     - 주로 개별 필드에 대한 유효성 검사를 수행하고
         *     해당 오류를 특정 필드에 연결하고자 할 때 사용합니다.
         *     - `rejectValue` 메서드는 필드 이름, 오류 코드,
         *     기본 오류 메시지 세 가지 매개변수를 받습니다.
         */
    }
}
