import React from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { useSearchParams } from '../../node_modules/react-router-dom/dist/index';

const profiles = {
  user01: {
    name: '이이름',
    age: 30,
  },
  user02: {
    name: '김이름',
    age: 50,
  },
};

const Profile = () => {
  const { username } = useParams();
  const { name, age } = profiles[username];

  //   const location = useLocation();
  //   console.log(location);

  // 쿼리스트링
  const [searchParams, setSearchParams] = useSearchParams();
  setSearchParams({ sopt: 'name', skey: '이이름' });
  console.log(searchParams);
  console.log(searchParams.get('sopt'));
  console.log(searchParams.get('skey'));

  return (
    <>
      <h1>
        <div>이름 : {name}</div>
        <div>나이 : {age}</div>
      </h1>
    </>
  );
};

export default React.memo(Profile);
