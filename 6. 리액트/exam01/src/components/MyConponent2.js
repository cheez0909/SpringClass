import PropTypes from 'prop-types';

const MyComponent2 = (props) => {
  const { name, age, children } = props;
  const v = Object.getOwnPropertyDescriptors(props);
  console.log(v);
  return (
    <>
      <div>나의 멋진 컴포넌트</div>
      <div>{name}님 안녕하세요</div>
      <div>나이는 {age}</div>
      <div>chidren : {children}</div>
    </>
  );
};

MyComponent2.defaultProps = {
  age: 20,
};

MyComponent2.propTypes = {
  age: PropTypes.number,
  name: PropTypes.string.isRequired,
};
export default MyComponent2;
