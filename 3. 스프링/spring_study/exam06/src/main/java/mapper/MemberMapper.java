package mapper;

import models.member.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {


    /**
     * 구현체가 프록시로 만들어짐
     */
    // XML 파일에 만들어 놓음
    // @Select("select * From member")
    List<Member> getMember(Member member);

//    @Insert("insert into member (USER_NO, USER_ID, USER_PW, USER_NAME) " +
//            "values (SEQ_member.nextval, #{userId}, #{userPw}, #{userName})")
    int register(Member member);

    int update(Member member);
    int delete(String userId);
}
