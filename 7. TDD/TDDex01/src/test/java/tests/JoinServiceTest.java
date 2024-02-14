package tests;

import jakarta.servlet.http.HttpServletRequest;
import member.controllers.JoinValidator;
import member.controllers.Member;
import member.service.BadRequestException;
import member.service.JoinService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("회원가입 테스트")
@ExtendWith(MockitoExtension.class)
public class JoinServiceTest {

    private JoinService joinService;

    @Mock
    private HttpServletRequest request;

    // 각 테스트 실행 전 호출
    // 초기자원을 정리할 떄 많이 사용
    @BeforeEach
    void init(){
        joinService = new JoinService(new JoinValidator());

        Member member = getMember();

//        request = mock(HttpServletRequest.class); // 가짜 객체를 만들어주어야함

        given(request.getParameter("userId")) // 매개변수가 이거 일때
                .willReturn(member.getUserId()); // 이 것을 반환

        given(request.getParameter("userPw"))
                .willReturn(member.getUserPw());

        given(request.getParameter("confirmPw"))
                .willReturn(member.getConfirmPw());

        given(request.getParameter("userNm"))
                .willReturn(member.getUserNm());
    }

    private Member getMember(){
        return Member.builder()
                .userId("user"+System.currentTimeMillis())
                .userPw("12345678")
                .confirmPw("12345678")
                .userNm("사용자")
                .build();
    }

//    // 각 테스트 실행 후 호출
//    @AfterEach
//    void afterEach(){
//        System.out.println("AfterEach");
//    }
//
//    // 정적인 메서드만 사용 가능 / 실행되기 전 한 번만
//    @BeforeAll
//    static void beforeAll(){
//        System.out.println("beforeAll");
//    }
//
//    @AfterAll
//    static void afterAll(){
//        System.out.println("afterAll");
//    }

    @Test
    @DisplayName("회원 가입 성공 시 예외 발생 없음")
    void joinSuccess(){
        Member member = new Member();
        assertDoesNotThrow(()->{joinService.join(getMember());});
    }

    @Test
    @DisplayName("회원가입 성공 테스트 - 요청 데이터")
    void joinSuccessWithRequest(){
        assertDoesNotThrow(()-> {
            joinService.join(request);
        });
    }

    @Test // @Disabled
    @DisplayName("필수 입력항목(userId, userPw, confirmPw, userNm) 검증, 실패 시 BadRequestException")
    void requiredField(){
        assertAll(
                ()-> requiredFieldTestEach("userId", "아이디"),
                ()->requiredFieldTestEach("userPw", "비밀번호"),
                ()->requiredFieldTestEach("confirmPw", "확인"),
                ()-> requiredFieldTestEach("userNm", "이름"));
    }

    // 의도했던 키워드가 있는지까지 체크
    private void requiredFieldTestEach(String field, String keyword){

        Member memberNull = getMember(); // 널체크
        Member memberBlank = getMember(); // 빈값체크


        if(field.equals("userId")){
            memberNull.setUserId(null);
            memberBlank.setUserId("          ");
        } else if(field.equals("userPw")){
            memberNull.setUserPw(null);
            memberBlank.setUserPw("          ");
            // memberBlank.setUserId("            ");
        } else if(field.equals("confirmPw")){
            memberNull.setConfirmPw(null);
            memberBlank.setConfirmPw("          ");
        } else if(field.equals("userNm")){
            memberNull.setUserNm(null);
            memberBlank.setUserNm("          ");
        }

        assertAll(
                ()->{
                    BadRequestException thrown = assertThrows(BadRequestException.class, () -> joinService.join(memberNull));
                    String message = thrown.getMessage();
                    assertTrue(message.contains(keyword));},
                () ->{
                    BadRequestException thrown = assertThrows(BadRequestException.class, ()->joinService.join(memberBlank));
                    String message = thrown.getMessage();
                    assertTrue(message.contains(keyword));}
        );
    }

    @Test
    void test1(){
        int num1 = 10;
        assertEquals(num1, 10);
    }

    @Test
    void test2(){
        boolean active = true;
        assertTrue(active);
    }

    @Test
    void test3(){
        String str = null;
        assertThrows(RuntimeException.class, ()->{
            if(str==null){
                throw new RuntimeException();
            }
        });
    }

    @Test
    void test4(){
        String str = "안녕하세요";
        assertDoesNotThrow(()->{
            if(str==null){
                throw new RuntimeException();
            }
        });
    }



}
