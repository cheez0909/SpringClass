import { Component } from 'react';
import ErrorMessage from './ErrorMessage';

class ErrorProcess extends Component {
  // 상태값이 필요함
  state = {
    // 에러 발생여부
    error: false,
    message: '',
  };

  // 에러 객체와 정보가 넘어옴
  componentDidCatch(error, info) {
    this.setState({
      error: true,
      message: error.message,
    });

    console.error(error, info);
  }

  render() {
    const { error, message } = this.state;
    return error ? <ErrorMessage>{message}</ErrorMessage> : this.props.children;
  }
}

export default ErrorProcess;
