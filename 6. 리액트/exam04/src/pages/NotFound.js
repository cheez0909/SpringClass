import React from 'react';
import { Link } from 'react-router-dom';

const NotFound = () => {
  return (
    <>
      <h1>페이지 없음</h1>
      <Link to="/">메인페이지로 이동</Link>
    </>
  );
};

export default React.memo(NotFound);
