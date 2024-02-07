import { useState } from 'react';

const Counter2 = () => {
  const [number, setNumber] = useState(0);

  return (
    <div>
      <h1>{number}</h1>
      <button type="button" onClick={() => setNumber(number - 1)}>
        -1
      </button>
      <button type="button" onClick={() => setNumber(number + 1)}>
        +1
      </button>
    </div>
  );
};

export default Counter2;
