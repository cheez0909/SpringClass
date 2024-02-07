function App() {
  const name = '이이름';

  const style = {
    backgroundColor: 'orange',
    fontSize: '3rem',
  };

  return (
    <>
      <h1
        className="title"
        // style={style}
      >
        안녕하세요!! {name || '기본이름'}{' '}
      </h1>
      {/*name && <h2>반갑습니다</h2>*/}
      <input type="text" />
    </>
  );
}

export default App;
