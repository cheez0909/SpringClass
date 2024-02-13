import { useReducer } from 'react';

function reducer(state, action) {
  // state - 기존 상태값
  switch (action.type) {
    case 'INCREMENT':
      return { number: state.number + 1 }; // 새로운 상태값
    case 'DECREMENT':
      return { number: state.number - 1 }; // 새로운 상태값
    default:
      return state;
  }
}

const Counter = () => {
  const [state, dispatch] = useReducer(reducer, { number: 0 });
  const { number } = state;
  return (
    <>
      <h1>{number}</h1>
      <button type="button" onClick={() => dispatch({ type: 'INCREMENT' })}>
        +1
      </button>
      <button type="button" onClick={() => dispatch({ type: 'DECREMENT' })}>
        -1
      </button>
    </>
  );
};

export default Counter;
