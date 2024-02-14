import { useState } from 'react';
import styles from './CSSModule.module.scss';
// import classNames from 'classnames';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const CSSModule = ({ name }) => {
  const [active, setActive] = useState(false);
  const onClick = () => {
    setActive(!active);
  };

  return (
    <>
      <header className={cx('wrap', { on: active })}>
        반갑습니다. <span className="title">{name}님...</span>
      </header>
      <button type="button" onClick={onClick}>
        버튼
      </button>
    </>
  );
};

export default CSSModule;
