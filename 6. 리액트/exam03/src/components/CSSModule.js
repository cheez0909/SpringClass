import { useState } from 'react';
import styles from './CSSModule.module.scss';
import classNames from 'classnames';

const CSSModule = ({ name }) => {
  
  const [active, setActive] = useState(false);
  const onClick = () => {
    setActive(!active);
  };

  return (
    <>
      <header className={styles.wrap}>
        반갑습니다.{' '}
        <span className={classNames('title', { on: active })}>{name}님...</span>
      </header>
      <button type="button" onClick={onClick}>
        버튼
      </button>
    </>
  );
};

export default CSSModule;
