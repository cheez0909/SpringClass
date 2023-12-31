hadlerMapping 
-> url과 요청 타입으로 컨트롤러를 찾음(형식이 다양하기 때문에 바로 실행 안하고 어댑터로 넘김)

handelerAdapter
-> 스프링 빈에서 해당 컨트롤러를 찾음
-> 실행 결과를 modelandView로 변환해서 리턴

viewResolver
-> 컨트롤러의 실행결과를 보여 줄 view 호출

모든 요청 -> 서블릿(dispatcher)

![img.png](images/img.png)

![img_1.png](images/img_1.png)


모든 요청(get/hello) -> DispatcherServler -> HadlerMapping 
-> HelloController 빈 -> HandlerAdapter -> HelloController::hello호출
->ModelAndView 객체 -> ViewResolver -> View 객체 검색 -> 응답



### 4. 실행부분에 대한 설명
HandlerAdapter -> HelloController::hello에 정의된 메서드
매개변수를 파악 -> 자동 주입 -> 호출

자동 주입
1) 요청 데이터 
-> @RequestParam("파라미터명 / name") 변수
-> 요청 데이터(Get, Post)중에서 주입
-> 변수의 자료형대로 자동으로 형변환 처리

2) 서블릿 기본 객체()
편의상 서블릿 기본 객체 -> 스프링 컨테이너에 빈으로 관리되고 있다
httpServletRequest
httpServletResponse
=HttpSession ...
```java
    @GetMapping("/hello")
    public String hello(HttpServletRequest requset, HttpServletResponse response, HttpSession session) {
        System.out.println("request : " + requset);
        System.out.println("reponse : " + response);
        System.out.println("session : " + session);
        return "hello";
    }
```
3) 스프링 WebMvc 기본 객체
Model -> 데이터용 객체 -> 추가 데이터는 EL식 변수 형태로 접근 가능
   (== request.setAttribute(...))


![img_2.png](images/img_2.png)
컨트롤러와 상관없이 직접 접근이 가능하도록 설정할 것

/mypage/** : /mypage 경로를 포함한 모든 하위 경로
 /mypage, /mypage/sub1, /mypage/sub1/sub2

/mypage/* : /mypage 경로를 포함한 바로 하위 경로
/mypage, /mypage/sub1, /mypage/sub2

? : 문자 1개
/m0? -> /m01 , /m02, /m0a ...

타임리프 기본 문법
1) 변수식 : ${식...}
속성 추가 없이 
[[${식}]] - th:text="${식}"

2) 메세지 식 : #{메세지 코드}
ex)


브라우저의 언어 설정 -> 요청 헤더 -> 서버 -> Locale -> 언어 파일 분리
병렬 리소스 방식
- commons.properties
- commons_em.properties

3) 링크식 : @{링크}
컨텍스트 경로 추가
URL변수 식, 요청 파라미터 쉽게 추사
참고) <c:url value="...." />

컨텍스트 경로 : request.getContextPath()
<c:url ---> 자동으로 컨텍스트 경로가 앞에 추가
@{/member/login(키=값, 키=값)} -> /exam07/member/login?키=값&키=값
@{/member/info/{id}(id=rkqt)}
{id} - 경로 변수

4) 선택 변수 식
![img_3.png](images/img_3.png)
th : object = "${객체}"
    *{속성명}
<section> 태그 대신 사용
th:block -> 태그로써 노출 X
![img_4.png](img_4.png)


th:text - 텍스트만 출력
th:utext - HTML도 해석될 수 있도록 출력

th:each -> 반복문
반복 상태 EL 객체
- index : 0부터 시작하는 순번
- count : 1부터 시작하는 순번
- first : 첫번째행
- last : 마지막행
- even : 짝수행
- odd : 홀수행
<c:forEach var="item" items="${...}" varStatus={status}>
</c:forEach>
![img_5.png](images/img_5.png)

5)th:if, th:unless : 조건식
th:if="${...}"식이 참일때 출력
th:unless="${...}" 식이 거짓일 때 출력

5-2)th:switch, th:case
6)th:href -> 모든 속성 th:속성 -> 속성="번역 내용"
th:href="@{....}"
th:src="@{...}"

7)th:object


스프링 MVC폼과 에러 메세지 연동
#fields.errors(...)
#fields.globalErrors(...)

![img_6.png](images/img_6.png)


타임리프 페이지 레이아웃
th:replace
th:fragment

자바 스크립트가 무겁기 때문에 layouts > common.html 파일 처럼 공통 부분을 빼놓고
필요할 경우 템플릿을 상속받아서 이용한다.

템플릿을 컨트롤러없이 Config파일에서 바로 연결할 경우
model을 사용할 수 없다.
이런 경우 내용치환기능을 이용할 수 있다.
![img_7.png](images/img_7.png)

@RequestMapping -> 모든 요청에 대한 매핑(Get, Post...) : 공통 URL 패턴 주로 정의

th:field - 입력 형태에 따라 value, checked, selected를 알아서 선택하거나 값을 입력

뷰에서 사용하는 데이터를 속성이라함
data = model

@ModelAttribute => 현재 컨트롤러 모든 url에서 공유할 데이터 설정시 사용

redirect, forward : 페이지 이동
요청 메서드의 반환값 : redirect:주소
RequestDispatcher
    forward -> 기존 버퍼를 취소하고 forward
    include -> 기존 버퍼에 포함시켜서 
    <jsp:forward page="...">
 => forward flush 하게됨


커맨드객체를 모델에 담아서 넘겨줌.......


커맨드 객체 검증
1) Validator 인터페이스


2) Errors
    reject("에러코드") - 메세지 코드로 에러코드가 등록된 경우 -> 출력
    reject("에러코드", "기본 메세지") - 에러코드가 메세지로 등록되지 않은 경우 기본 메세지

    -- 특정 필드에 해당하는 에러 메세지 처리 --
    rejectValue("필드명", "에러코드");
    rejectValue("필드명", "에러코드", "기본메세지");

    boolean hasErrors() : reject 또는 rejectValue가 한개라도 호출 되면 -> true

3) ValidationUtils

에러 코드에 해당하는 메시지 코드를 찾는 규칙
- 에러코드 + "." + 

- 에러 출력 -> #fields.errors("필드명");

ValidationUtils
 - .rejectvalueif
 - .reject
![img_8.png](images/img_8.png)


Bean Validation
 - Bean Validation API `implementation 'jakarta.validation:jakarta.validation-api:3.0.2'` 
-> 구현체가 필요함 (Hibernate Validator를 사용함) / API는 구현체가 반드시 필요함
 - Hibernate Validator

![img_9.png](images/img_9.png)




![img_10.png](images/img_10.png)



![img_11.png](images/img_11.png)
validator 인터페이스를 통해서

커맨드 객체
1) 핸들러 outer(핸들러 아웃터 중의 하나는 ViewResolver입니다.)에서 실행할때 다 set해줌... @Data를 설정해두고 name값과 동일하게 필드명 입력


RequstJoin -> requestJoin : model의 속성명으로 추가 -> 템프릿 내에서 바로 접근 가능

th:field -> 속성에 따라서 th:select.. 등등 알아서 됨....
커맨드객체 검증에서 출력된 것도 th:field에서 

2) 커맨드 객체 검증 (Bean Validator API)





=======================================================
세션&쿠키 -> 로그인할때 사용하는...

쿠키 : 개인 서비스 구현 기술
개개인을 어떻게 구분?
-> 브라우저별로 데이터를 저장함으로써 개개인의 데이터를 유지한다.

개인 구분 서비스는 서버가 제공함
-> 서버는 개개인의 데이터가 필요함 
-> 요청시마다 쿠키값을 요청헤더에 담아 전달함

서블릿내에서 쿠키를 등록함
응답헤더에 set-Cookie에 키와 값형태로 브라우저에 쿠키가 등록됨
HttpServletResponse::addCookie(Cookie cookie)
-> 이렇게 설정된 쿠키는 브라우저에 저장되고,
이후의 요청에서는 해당 세션 식별자가 함께 전송되어
서버가 사용자를 식별하고 세션을 관리할 수 있게 됩니다.


쿠키 조회
요청 헤더 : Cookie 항목으로 전송
HttpServletRequest
    Cookie[] getCookies()
> 중요한 데이터를 쿠키에 담게 되면 개인정보가 유출될 가능성이 있음...
1) 요청 시마다 헤더에 실려서 서버에 전송됨 -> 노출이 되면서 보안에 취약하다
=> 개인정보는 절대 사용 불가....
=> 쿠키는 암호화가 필수

2) 요청시마다 헤더에 실려서 서버에 전송되기 때문에 전송데이터가 많아지는 문제가 생김 
즉, 네트워크에 부담이 된다.

=> 이러한 문제점을 해결하기 위한 기술 : 세션!!!

스프링MVC
1. 세션 
    => 개인 서비스 데이터를 직접 서버에 저장
    => 개인 데이터를 찾으려면?
        - 브라우저 마다 jsessionid를 저장 : 개개인의 브라우저를 구분할 수 있는 값
        - 요청시마다 요청헤더 Cookie : JSESSIONID=..... 를 통해서 서버에 전송
        - 요청 처리 중에 JSESSIONID -> 서버쪽에 저정된 개인 데이터를 추출 -> session 객체 생성
        - HttpServletRequest : HttpSession getSession()
    => HttpSession session
            - setAttribute(String name, Object value)
            - Object getAttribute(String name)
            - void removeAttribute(String name)
            - invalidate() : 세션 비우기...!

스프링에서 지원하는 쿠키개별조회
@CookieValue 쿠키명과 동일한 변수명
    - 쿠키 개별 조회
    - 특정 변수에 주입, 형변환도 자동
![img_19.png](images/img_19.png)
쿠키값을 주입해줌








![img_18.png](images/img_18.png)
   세션 (Session): 세션은 사용자가 웹 애플리케이션에 접속한 시간부터 
브라우저를 종료하거나 특정 시간 동안 활동이 없을 때까지의 일련의 상호 작용을 
나타냅니다. 보통 세션은 일정 기간 동안 지속되며, 사용자의 상태를 
유지하고 관리하는 데 사용됩니다.

세션 ID (Session ID): 
세션 ID는 세션을 식별하는 데 사용되는 고유한 식별자입니다. 
보통은 랜덤하게 생성된 문자열이나 숫자로 구성되며, 
세션 ID를 통해 서버는 특정 세션을 식별하고 해당 세션과 관련된 정보를 유지합니다. 
클라이언트는 세션 ID를 쿠키나 URL 매개변수 등을 통해 서버에 전달하여 세션을 식별합니다.

간단히 말해서, 세션은 사용자의 활동 기간을 나타내고, 
세션 ID는 그 세션을 식별하는 데 사용되는 고유한 식별자입니다. 
세션 ID는 일반적으로 보안적인 이유로 랜덤하게 생성되며, 
세션 ID를 통해 서버는 사용자의 상태를 관리하고 유지할 수 있습니다.

2. 인터셉터




![img_12.png](images/img_12.png)
testController > 쿠키가 등록되었따.


![img_13.png](images/img_13.png)
>/cookie1/test2 경로로 들어갔을땐 쿠키가 생성되지 않음
> 경로가 바뀌면 쿠키가 유지되지 않음


![img_14.png](images/img_14.png)
> 만료시간을 설정함으로써 제어할 수 있음
> 기본값이 세션으로 입력된 것은 브라우저가 열려있는 동안만 유지가 된다.
> 즉 만료시간을 설정하지 않으면 기본값 = 브라우저가 열려있는 동안만 유지가 된다.
> 
> 
> 
> 
![img_15.png](images/img_15.png)
한국은 +9



![img_16.png](images/img_16.png)



>httpOnly : 자바스크립트 document.cookie로 조회가 되지 않음
![img_17.png](images/img_17.png)
> 
> 
> 
> 
### 인터셉터
![img_20.png](images/img_20.png)

1) HandlerInterceptor 인터페이스
- boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

- void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;
  : 컨트롤러 빈 실 행 후, 응답 실행 전 호출
  : 컨틀로러 빈 실 행 후 공통적인 처리 

- void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;
  : 응답 완료 후 호출
  : 응답 완료 후 공통적인 정리 작업..

> preHandle 을 가장 많이 사용함 -> 인가에 대한 부분에서 이용
> postHandle 응답 전에 공통적인 것을 할 때



![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
> 모든 주소에 설정을 추가함
> 보통 모바일 버전에서 pc버전으로 보고 싶을때... 이러한 설정 정보를 모든 주소에 추가할 때



@DateTimeFormat
- LocalDate, LocalTime, LocalDateTime .. - JSR310 - Date & Time API

#### 마이바티스에서는 localdatetune을 사용하려면 추가적인 설정이 필요함



typeMismatch 
-> 형식이 일치하지 않으면 예외 발생
-> 꼭 날짜가 아니어도 발생함....

메세지 코드에 넣어보기
typeMismatch.java.time.LocalDate

#### 범위를 좁힐 수 있다.
메세지코드
메세지코드.필드명
메세지코드.커맨드객체명.필드명
메세지코드.자료형 타입
메세지코드.커맨드객체명.자료형 타입

@PathVariable -> 경로 변수
변수가 경로안에 있는것


![img_3.png](img_3.png)
1개당 최대 용량 / 전체 용량
kbyte - 1024byte
1mb - 1024 kbyte


### 스프링 파일 업로드

- <form> 속성 :  `enctype="multipart/form-data"`
위 속성이 없으면 키와 값 형태로 전송됨 (문자열 : (일반텍스트형식으로 전달됨))![img_4.png](img_4.png)
> 속성을 추가하면 헤더값이 바뀌어 있음
![img_5.png](img_5.png)![img_6.png](img_6.png)![img_7.png](img_7.png)

파트를 나누어서.. 이쪽은 일반양식데이터(일반텍스트형식으로 전달됨) 이쪽은 파일데이터(바이너리데이터) 이런식으로 나누기 때문에 멀티파트라고함<br>

commons-io :  I/O 작업을 쉽게 다룰 수 있는 라이브러리로, 파일 복사, 삭제, 이동 등 다양한 파일 관련 작업을 지원
commons-fileupload : HTTP 요청에서 멀티파트 파일을 추출하는 데 사용됩니다.

#### MultipartFile 인터페이스 

![img_8.png](img_8.png)

getOriginalFilename() 
-> 서버에 올릴때는 다른 파일이름으로 올리고 다운 받을때는 원래 파일 명으로
-> why? 서버에는 파일이름이 동일한 파일이 여러개 있을 수 있음
-> DB에 파일 정보를 입력해놓음..


비동기방식 추가가됨 자바 io

만약에 이미지 파일을 올렸을때 웹에서 이미지가 보이도록
서버쪽에 접근이 가능하도록 설정해야함

> addResourceHandlers 설정

<br>
<br>

#### 프로필
@Profile

        /**
         * 배포 시에 DB연결 설정을 분리함 -> profile
         * -> 배포 시엔 ~Bean
         * -> 개발 시엔 **Bean
         * 따로 만들 수 있음
         */

@Value