package member.controllers;

import member.commons.RequiredValidator;
import member.commons.Validator;
import member.service.BadRequestException;

public class JoinValidator implements Validator<Member>, RequiredValidator {
    @Override
    public void check(Member member) {
        String userId = member.getUserId();
        String userPw = member.getUserPw();
        String confirmPw = member.getConfirmPw();
        String userNm = member.getUserNm();

        checkRequired(userId, new BadRequestException("아이디를 입력하세요"));
        checkRequired(userPw, new BadRequestException("비밀번호를 입력하세요"));
        checkRequired(confirmPw, new BadRequestException("비밀번호확인란을 입력하세요"));
        checkRequired(userNm, new BadRequestException("이름을 입력하세요"));
    }
}
