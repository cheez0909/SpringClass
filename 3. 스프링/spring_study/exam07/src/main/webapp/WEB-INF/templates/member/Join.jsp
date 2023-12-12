<%@ page contentType="text/html; charset=utf-8" %>
<form method="post">
    <dl>
        <dt>아이디</dt>
        <dd>
            <input type="text" name="id" />
        </dd>
    </dl>
     <dl>
        <dt>비밀번호</dt>
        <dd>
            <input type="password" name="pw" />
        </dd>
    </dl>
    <dl>
    <dt>비밀번호 확인</dt>
    <dd>
    <input type="password" name="pwcheck" />
    </dd>
    </dl>
    <dl>
    <dt>회원명</dt>
    <dd>
    <input type="text" name="name" />
    </dd>
    </dl>
    <dl>
    <dt>이메일</dt>
    <dd>
    <input type="text" name="email">
    </dd>
    </dl>
    <div>
    <input type="checkbox" name="agree" value="true" id="agree">
    <label for="agree">회원가입 약관에 동의합니다.</label>
    </div>
    <button type="submit">가입하기</button>
</form>
