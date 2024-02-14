import React, { useCallback, useState } from 'react';
import { SmallButton as LoginButton } from './commons/StyledButton';
import { FiLogIn, FiSquare, FiCheckSquare } from 'react-icons/fi';

// console.log(styled.button instanceof Function); true

const LoginForm = () => {
  const [saveId, setSaveId] = useState(false);
  // 함수 캐싱
  const onToggle = useCallback(() => setSaveId((saveId) => !saveId), []);
  return (
    <>
      <h1>로그인</h1>
      <form>
        <input type="text" name="userId" placeholder="아이디" />
        <input type="password" name="password" placeholder="비밀번호" />
        <div onClick={onToggle}>
          {saveId ? <FiCheckSquare /> : <FiSquare />} 아이디 저장
        </div>
        <LoginButton type="submit" bColor="primary" color="#fff" active={true}>
          <FiLogIn />
          로그인
        </LoginButton>
      </form>
    </>
  );
};

export default React.memo(LoginForm);
