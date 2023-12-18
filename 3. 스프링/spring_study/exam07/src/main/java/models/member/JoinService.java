package models.member;


import controllers.member.RequestJoin;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberDao memberDao;
    public void join(RequestJoin form){
        // 비밀번호 해시 생성, 해시를 몇번 반복할건지
        String hash = BCrypt.hashpw(form.getPw(), BCrypt.gensalt(12));

        Member member = Member.builder()
                .userId(form.getId())
                .userName(form.getName())
                .userPw(hash)
                .email(form.getEmail())
                .build();

        memberDao.register(member);

        // 비밀번호는 암호화 하는 게 좋음
        // 양방향 암호화
        //      암호화 - 복호화
        //      AES256
        //      ARIA

        // 단방향 암호화
        //          - 복호화는 불가함
        //          - 해시
        //          - 고정 해시 : sha1, md5, sha256, sha512 ....
        //              : 같은 값에 대해서 동일한 해시 값
        //              : 요즘엔 잘 안씀(고정값이기 때문에 하나하나 매칭하다보면 비밀번호를 알 수 있음)
        //          - 유동 해시 : 같은 값에 대해서 해시를 만들때마다 다른 해시값
//                            :   예측 불가능성
//                            :   BCrypt

    }
}
