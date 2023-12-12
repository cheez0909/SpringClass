package models.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
