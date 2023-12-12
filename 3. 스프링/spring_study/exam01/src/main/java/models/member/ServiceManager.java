package models.member;

public class ServiceManager {

    private static ServiceManager instance = null;

    public static ServiceManager getInstance(){
        if(instance==null){
            instance = new ServiceManager();
        }
        return instance;
    }

    private ServiceManager(){
    }

    public MemberDao memberDao(){
//        return new MemberDao();
        return new CachedMemberDao(); // 다형성
    }

    public JoinValidator joinValidator(){
        return new JoinValidator(memberDao());
    }
    public JoinService joinService(){
        return new JoinService(memberDao(), joinValidator());
    }

    public MemberListService memberListService(){
        MemberListService memberListService = new MemberListService();
        memberListService.setMemberDao(memberDao());
        return memberListService;
    }
}
