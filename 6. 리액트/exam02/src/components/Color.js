import { useState, useEffect } from 'react';
import ColorSelect from './ColorSelect';

const Color = () => {
  const [color, setColor] = useState('gray');

  useEffect(() => {
    console.log('색상 변경:', color);

    return () => {
      console.log('렌더링 전 뒷정리...'); // 작업을 해제한다거나 할때...
    };
  }, [color]); // 두번째 매개변수가 변화감지의 기준이 됨

  const boxStyle = {
    width: '200px',
    height: '200px',
    backgroundColor: color,
  };

  return (
    <>
      <div style={boxStyle}></div>
      <ColorSelect setColor={setColor}></ColorSelect>
    </>
  );
};

export default Color;
