import LifeCycle from './components/LifeCycle';
import { useState } from 'react';

const App = () => {
  const [color, setColor] = useState('black');
  return (
    <>
      <LifeCycle color={color} />
      <button type="button" onClick={() => setColor('red')}>
        red
      </button>
      <button type="button" onClick={() => setColor('blue')}>
        blue
      </button>
      <button type="button" onClick={() => setColor('orange')}>
        orange
      </button>
    </>
  );
};

export default App;
