package models.member;


import lombok.RequiredArgsConstructor;
import mapper.MemberMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final MemberMapper mapper;

    public boolean register(Member member){
        int affectedRows = mapper.register(member);

        return affectedRows > 0;
    }

    public boolean exist(String userId){
        int cnt = mapper.exist(userId);
        return cnt > 0; // 1이상이면 이미 등록된 아이디
    }

    public Member get(String userId){
        return mapper.get(userId);
    }


}
