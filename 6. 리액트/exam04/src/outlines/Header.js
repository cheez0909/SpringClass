import React from 'react';
import { useNavigate, Navigate, NavLink } from 'react-router-dom';
import classNames from 'classnames';

const Header = () => {
  const navigate = useNavigate();
  const isLogin = true;

  if (!isLogin) {
    return <Navigate to="/login" />;
  }

  return (
    <>
      <h1>헤더 영역</h1>
      <button
        type="button"
        onClick={() => navigate('/login', { replace: true })}
      >
        로그인
      </button>
      <button type="button" onClick={() => navigate(-1)}>
        back
      </button>
      <button type="button" onClick={() => navigate(1)}>
        forward
      </button>

      <NavLink
        to="/about"
        className={({ isActive }) => classNames({ on: isActive })}
      >
        About페이지
      </NavLink>
    </>
  );
};

export default React.memo(Header);
