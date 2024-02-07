import React, { useState, useEffect, useRef } from 'react';

const LoginForm = () => {
  const [form, setForm] = useState({});

  const [msg, setMsg] = useState('');

  //   const [userId, setUserId] = useState('');
  //   const [userPw, setUserPw] = useState('');

  const onSubmit = (e) => {
    e.preventDefault();

    countRef.current++;
    console.log(countRef.current);

    try {
      for (const key in form) {
        const message =
          (key === 'userId' ? '아이디' : '비밀번호') + '를 입력하세요';
        if (!form[key].trim()) {
          throw new Error(message);
        }
      }
    } catch (err) {
      setMsg(err.message);
    }

    // console.log(userId);
    // console.log(userPw);
  };
  const { userId, userPw } = form;

  const onChange = (e) => {
    console.log(e.currentTarget.name, e.currentTarget.value);
    setForm({ ...form, [e.currentTarget.name]: e.currentTarget.value });
    console.log(form);
  };
  // ref를 사용함(ID사용 지양) -> 콜백함수 형태로 사용
  // let userIdEl;

  // let userIdEl = React.createRef();

  let userIdEl = useRef();

  // let countRef = 1;
  const countRef = useRef(1);

  useEffect(() => {
    // userIdEl.focus();
    // console.log(userIdEl);
    console.log(userIdEl.current); // 돔 객체
    userIdEl.current.focus();
  }, [userIdEl]);

  return (
    <>
      <form onSubmit={onSubmit}>
        <dl>
          <dt>아이디</dt>
          <dd>
            <input
              // ref={(ref) => (userIdEl = ref)}
              ref={userIdEl}
              type="text"
              name="userId"
              onChange={onChange}
              value={userId}
            />
          </dd>
        </dl>
        <dl>
          <dt>비밀번호</dt>
          <dd>
            <input
              type="password"
              name="usePw"
              onChange={onChange}
              value={userPw}
            />
          </dd>
        </dl>
        {msg && <div>{msg}</div>}
        <button type="submit">로그인</button>
      </form>
    </>
  );
};

export default LoginForm;
