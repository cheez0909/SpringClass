import React, { useState, Suspense } from 'react';
import { Link } from 'react-router-dom';
import loadable from '@loadable/component';

// const SplitMe = import('../components/SplitMe');

const Home = () => {
  // const [LoadComponent, setLoadComponent] = useState(null);
  // const onClick = () =>
  //   SplitMe.then((result) => setLoadComponent(result.default));

  // const SplitMe = React.lazy(() => import('../components/SplitMe'));

  // 로더블 사용
  const SplitMe = loadable(() => import('../components/SplitMe'), {
    fallback: <div>Loading...</div>,
  });
  const [visible, setVisible] = useState(false); // 클릭하면

  const onClick = () => {
    setVisible(true);
  };

  return (
    <>
      <h1>HOME</h1>
      <Link to="/about">About 페이지 이동</Link>
      <button type="button" onClick={onClick}>
        클릭!
      </button>
      {/* 로더블 사용시 */}
      {visible && <SplitMe />}

      {/* 서스펜스 사용 시 */}
      {/* <Suspense fallback={<div>Loading...</div>}>
        {visible && <SplitMe />}
      </Suspense> */}
    </>
  );
};

export default React.memo(Home);
