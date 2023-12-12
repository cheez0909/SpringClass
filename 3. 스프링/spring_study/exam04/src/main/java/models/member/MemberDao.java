package models.member;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDao {
    private static Map<String, Member> memberMap  = new HashMap<>();


    public void register(Member member){
        memberMap.put(member.getUserId(), member);
    }

    public boolean exists(String userId){
        return memberMap.containsKey(userId);
    }

    public List<Member> getList(){
        return new ArrayList<>(memberMap.values());
    }
    public Member getOne(String id){
        return memberMap.get(id);
    }

}
