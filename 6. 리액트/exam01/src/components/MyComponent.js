import { Component } from 'react';
import PropTypes from 'prop-types';

class Mycomponent extends Component {
  static defaultProps = {
    name: '기본이름',
  };

  static propTypes = {
    age: PropTypes.number,
    name: PropTypes.string.isRequired,
  };

  render() {
    const { name, age, children } = this.props;

    return (
      <>
        <h1>나의 컴포넌트</h1>
        <div>{name}님 안녕하세요</div>
        <div>{age}살 입니다</div>
        <div>children : {children}</div>
      </>
    );
  }
}

// Mycomponent.defaultProps = {
//   name: '기본이름',
// };

// Mycomponent.propTypes = {
//   age : PropTypes.number,
//   name : PropTypes.string.isRequired
// };

export default Mycomponent;
