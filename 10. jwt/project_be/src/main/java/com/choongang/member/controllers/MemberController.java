package com.choongang.member.controllers;

import com.choongang.commons.exceptions.BadRequestException;
import com.choongang.commons.rests.JSONData;
import com.choongang.member.services.MemberLoginService;
import com.choongang.member.services.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSaveService saveService;
    private final JoinValidator joinValidator;
    private final MemberLoginService memberLoginService;
    @PostMapping
    public ResponseEntity join(@Valid @RequestBody RequestJoin form, Errors errors){
        joinValidator.validate(form, errors);
        errorProcess(errors);
        saveService.join(form);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 응답코드만 넣고 바디는 없이
    }

    private void errorProcess(Errors errors){
        if(errors.hasErrors()){
            throw new BadRequestException(errors);
        }
    }

    @PostMapping("/token")
    public JSONData login(@Valid @RequestBody RequestLogin form, Errors errors){
        errorProcess(errors);

        String token = memberLoginService.login(form);
        return new JSONData(token);
    }

    @GetMapping("/member_only")
    @PreAuthorize("isAuthenticated()")
    public void memberOnlyTest(){
        System.out.println("회원 전용 페이지 접근");
    }

    @GetMapping("/admin_only")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminOnlyTest(){
        System.out.println("관리자 전용 페이지 접근");
    }


}
