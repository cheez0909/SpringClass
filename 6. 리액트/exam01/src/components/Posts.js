import { useEffect, useState } from 'react';

const Posts = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((res) => res.json())
      // .then((res) => console.log(res))
      .then((posts) => {
        setItems(posts);
        setLoading(false);
      });
  }, []);

  return (
    <>
      <h1>게시글 목록</h1>
      {loading && <p>Loading...</p>}
      {/* 비구조화 할당을 이용하기도 함 */}
      <ul>
        {items && items.map(({ title, id }) => <li key={id}>{title}</li>)}
      </ul>
    </>
  );
};

export default Posts;
