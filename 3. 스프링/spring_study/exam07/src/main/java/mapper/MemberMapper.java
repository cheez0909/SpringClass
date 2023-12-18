package mapper;

import models.member.Member;

public interface MemberMapper {
    int register(Member member);
    int exist(String userId);
    Member get(String userId);

}
