package mabatistest;


import config.AppCtx;
import mapper.MemberMapper;
import models.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
// 컨테이너를 만들어 놨음
@ContextConfiguration(classes = AppCtx.class)
public class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("멤버 리스트 조회")
    void memberListTest(){
        Member member = Member.builder().userId("USER").build();
        memberMapper.getMember(member).stream().forEach(System.out::println);
        // System.out.println(memberMapper.getClass().getName()); // 프록시
    }

    @Test
    @DisplayName("멤버 추가 테스트")
    void memberInsertTest(){
        Member member = Member.builder()
                .userId("USER2010")
                .userPw("123456")
                .userName("사용자113")
                .email("uesr010@test.org")
                .build();
        int register = memberMapper.register(member);
        System.out.println(register);
    }

    @Test
    @DisplayName("멤버 수정하기")
    void memberUpdateTest(){
        Member member = Member.builder().userId("USER2010")
                .userPw("123456").build();
        int update = memberMapper.update(member);
        System.out.println(update);
    }

    @Test
    @DisplayName("멤버 삭제하기")
    void memberDeleteTest(){
        int delete = memberMapper.delete("USER2010");
        System.out.println(delete);
    }
}
