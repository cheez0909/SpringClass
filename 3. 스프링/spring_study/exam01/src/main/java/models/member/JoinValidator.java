package models.member;

import common.Validator;
import common.exceptions.BadRequesetException;
import common.validator.RequiredValidator;

/*
* 유효성 검사
* */
public class JoinValidator implements Validator<Member>, RequiredValidator {

    private MemberDao memberDao;

    public JoinValidator(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public void validate(Member member){
        /*
        *  필수 항목 검증(아이디, 비밀번호, 비밀번호확인, 회원명)
        *    - 널이 아니고, 공백이 아니어야함
        * */
        String userId = member.getUserId();
        String userPw = member.getUserPw();
        String confirmPw = member.getConfirmPw();
        String userName = member.getUserName();

        // 필수 항목 - null or 빈 공백
        checkRequired(userId, new BadRequesetException("아이디를 입력하세요"));
        checkRequired(userPw, new BadRequesetException("비밀번호를 입력하세요"));
        checkRequired(confirmPw, new BadRequesetException("비밀번호를 확인하세요"));
        checkRequired(userName, new BadRequesetException("이름를 입력하세요"));
//        if(userId==null || userId.isBlank()){
//            System.out.println("아이디를 입력해 주세요");
//        }
//        if(userPw==null || userPw.isBlank()) {
//            System.out.println("비밀번호를 입력해 주세요");
//        }
//
//        if(userName==null || userName.isBlank()){
//            System.out.println("이름을 입력하세요");
//        }

        // 비밀번호, 비밀번호 확인 시 일치 여부
//        if(userPw.equals(confirmPw)){
//
//        }

        // 비밀번호 일치 여부
        checkTrue(userPw.equals(confirmPw), new BadRequesetException("비밀번호가 일치하지 않습니다."));

        // 이미 등록된 아이디 인지
        checkFalse(memberDao.exists(userId), new BadRequesetException("이미 등록된 아이디 입니다."));
    }
}
