import { Component } from 'react';

class LifeCycle extends Component {
  state = {
    number: 0,
    color: 'black',
  };

  static getDerivedStateFromProps(nextProps, prevState) {
    // 부모가 주는 프롭스는 nextProps에 담겨 있음
    if (nextProps.color === 'orange') {
      return { color: nextProps.color };
    }
    return null; // 널나오면 반영X
  }

  // 렌더 다음에 호출 됨, 돔이 완성되고 난 후 구성
  componentDidMount() {
    console.log('componentDidMount');
  }

  // 업데이트 됐을 때 렌더링을 다시 하지 않도록
  /*
  shouldComponentUpdate(nextProps, nextState) {
    if (nextState.number % 2 === 1) {
      return false;
    }
    return true;
  }
  */

  getSnapshotBeforeUpdate(prevProps, prevState) {
    return 'snapshot';
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log('componentDidUpdate');
    console.log('snapshot', snapshot); // 업데이트된 값이 넘어옴
    console.log('prevState', prevState);
  }

  render() {
    console.log('render');

    const result = false;
    if (!result) {
      throw new Error('에러 발생!!!!(￣︶￣*))');
    }

    const { number, color } = this.state;
    return (
      <>
        <h1>{number}</h1>
        <h2>{color}</h2>
        <button
          type="button"
          onClick={() => this.setState({ number: number - 1, color })}
        >
          -1
        </button>
        <button
          type="button"
          onClick={() => this.setState({ number: number + 1, color })}
        >
          +1
        </button>
      </>
    );
  }
}

export default LifeCycle;
