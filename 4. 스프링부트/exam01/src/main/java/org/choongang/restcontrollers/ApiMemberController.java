package org.choongang.restcontrollers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.choongang.commons.BadRequestException;
import org.choongang.commons.JSONData;
import org.choongang.entites.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class ApiMemberController {


    /**
     * 제이슨 형태로 요청하는 데이터임을 알려줘야 함
     */
//    @PostMapping
//    // 서버쪽에도 에러메세지를 전달하려면 반환값을 void가 아닌 String으로
//    public String join(@RequestBody @Valid RequestJoin form, Errors errors){
//        /**
//         * 에러 발생여부도 체크가능하지만, 에러 정보도 호출 가능
//         */
//        if(errors.hasErrors()){
//            List<String> list = errors.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .toList();
//            log.info("에러 : {}", list.toString()); // 콘솔에 에러메세지 출력
//            // 반환값을 String타입으로 List -> String으로 변환
//            String msg = list.stream().collect(Collectors.joining(", ")).toString();
//            // 에러를 분리하기 위해 예외를 던진다.
//            throw new RuntimeException(msg);
//        }
//        log.info(form.toString());
//        return "OK";
//    }

//    @PostMapping
//    public ResponseEntity join(@RequestBody @Valid RequestJoin form, Errors errors){
//
//        if(errors.hasErrors()){
//            List<String> list = errors.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .toList();
//            log.info("에러 : {}", list.toString());
//            String msg = list.stream().collect(Collectors.joining(", ")).toString();
//            throw new RuntimeException(msg);
//        }
//        log.info(form.toString());
//        // 응답 코드 - 201, Body - 없음
//        // 응답코드 번호 직접입력 보단 상수로 입력하는 것이 좋음
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .header("CUSTOM_HEADER", "value1") // 커스텀헤더
//                .build();
//    }
    @PostMapping
    public ResponseEntity<JSONData> join(@RequestBody @Valid RequestJoin form, Errors errors) {
        if (errors.hasErrors()) {
            List<String> list = errors.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            log.info("에러 : {}", list.toString());
            String msg = list.stream().collect(Collectors.joining(", "));
            // throw new RuntimeException(msg);
            throw new BadRequestException(msg);
        }

        // 응답 코드 - 201, Body - 없음

        /*
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("CUSTOM_HEADER", "value1") // 커스텀헤더
                .build();

         */
        HttpStatus status = HttpStatus.CREATED;
        JSONData<Object> data = new JSONData<>();
        data.setHttpStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

//    @GetMapping
//    public Member info(){
//        Member member = Member.builder()
//                .userNo(1L)
//                .userPw("1234567")
//                .userId("user09")
//                .userName("사용자01")
//                .email("user09@test.org")
//                .regDt(LocalDateTime.now())
//                .modDt(LocalDateTime.now())
//                .build();
//        return member;
//    }

    /*
    @GetMapping
    public JSONData<Member> info(){
        Member member = Member.builder()
                .userNo(1L)
                .userPw("1234567")
                .userId("user09")
                .userName("사용자01")
                .email("user09@test.org")
                .regDt(LocalDateTime.now())
                .modDt(LocalDateTime.now())
                .build();
        //JSONData<Member> data = new JSONData<>();
        //data.setData(member);
        return new JSONData<>(member);
    }
     */


    /**
     * 제이슨 문자열을 자바 객체로 바꾸는 테스트릴 위해 잠시 생성해둔 메서드
     */
    @GetMapping
    public Member info(){
        Member member = Member.builder()
                .userNo(1L)
                .userPw("1234567")
                .userId("user09")
                .userName("사용자01")
                .email("user09@test.org")
                .regDt(LocalDateTime.now())
                .modDt(LocalDateTime.now())
                .build();
        //JSONData<Member> data = new JSONData<>();
        //data.setData(member);
        return member;
    }

    @GetMapping("/s")
    public String list1(){
        return "안녕";
    }

    @GetMapping("/list")
    public List<Member> list(){
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i-> Member.builder().userNo(Long.valueOf(i)).userId("user" + i)
                        .userPw("12345678")
                        .userName("사용자"+i)
                        .email("test"+i+"@test.org")
                        .regDt(LocalDateTime.now())
                        .modDt(LocalDateTime.now())
                        .build()).toList();
        return members;
    }

    @GetMapping("/message")
    @ResponseBody
    public String message(){
        return "안녕?";
    }
    /**
     * 반환값없이 상태코드만 내보내거나
     * 처리만 진행하는 메서드도 있음!
     */
    @GetMapping("/process")
    public void process(){
        System.out.println("처리...");
    }
/**
 * 에러가 던져지면 해당 메서드가 실행된다
 */
//    @ExceptionHandler(Exception.class)
//    public String errorHandler(Exception e){
//
//        return e.getMessage();
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity errorHandler(Exception e){
//       // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
