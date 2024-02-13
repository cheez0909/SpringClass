import React, { useState, useMemo, useCallback } from 'react';

function getAverage(list) {
  const total = list.reduce((a, b) => a + b, 0);
  const avg = Math.round((total * 100) / list.length) / 100; // 소수점 둘째 자리까지
  console.log('getAverage호츌');
  return avg;
}

const Average = () => {
  const [number, setNumber] = useState(0);
  const [list, setList] = useState([]);

  const onChange = useCallback(
    (e) => setNumber(parseInt(e.currentTarget.value)),
    [],
  );

  /*
  const onClick = useCallback(() => {
    setList(list.concat([number]));
    setNumber('');
  }, [list, number]);
  */

  const onClick = useCallback(() => {
    setList(() => list.concat(number));
    setNumber('');
  }, [number]);

  const avg = useMemo(() => getAverage(list), [list]);

  return (
    <>
      <div>
        <div>
          <input type="number" onChange={onChange} value={number} />
          <button type="button" onClick={onClick}>
            평균구하기
          </button>
        </div>
        <ul>
          {list.map((number, i) => (
            <li key={i}>{number}</li>
          ))}
        </ul>
        <div>평균 : {avg}</div>
      </div>
    </>
  );
};

export default React.memo(Average);
