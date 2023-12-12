package models.member;

import config.Manual;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberListService {
    @Autowired
    private MemberDao memberDao;

    // @Autowired
    // private Optional<DateTimeFormatter> dateTimeFormatter;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:MM");
// 자동 주입대상이 없을 경우 메서드 자체가 호출되지 않음
//    @Autowired(required = false)
//    public void setDateTimeFormatter(Optional<DateTimeFormatter> dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }

    // @Autowired
//    @Autowired(required = false)
//    public void setDateTimeFormatter(/*@Nullable*/  DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }

    public MemberListService(){
    }

    public Member getOne(Member member){
        return memberDao.getOne(member.getUserId());
    }


    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 회원 목록 조회
    // .format()의 리턴값은 String이다
    // 멤버 클래스에서 포맷된 날짜를 넣을 멤버변수가 필요하다.
    // LocalDateTime으로 새로 set할 수 없다. 자료형이 다르고
    // String -> LocalDateTime으로 타입변환이 되지 않는다.
    public void getList(){
        List<Member> list = memberDao.getList();
        for(Member num : list){
//            if(dateTimeFormatter!=null) { // 널 체크를 안 할 경우 오류가 발생함
//            DateTimeFormatter formatter = dateTimeFormatter.orElse(null);
            num.setRegDtSt(num.getRegDt().format(dateTimeFormatter));
                num.setRegDtSt(dateTimeFormatter.format(num.getRegDt()));
//            }
        }
        list.stream().forEach(x -> System.out.println(x));
    }


}
