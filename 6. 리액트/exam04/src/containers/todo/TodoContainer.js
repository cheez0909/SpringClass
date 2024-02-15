import React, { useState, useCallback } from 'react';
import TodoForm from '../../components/todo/TodoForm';
import TodoList from '../../components/todo/TodoList';
import styled from 'styled-components';
import { produce } from 'immer';

const ContentBox = styled.div`
  box-sizing: border-box;
  width: 100%;
  max-width: 720px;
  margin: 0 auto;
  background: #f8f8f8;
  padding: 25px;
  * {
    box-sizing: border-box;
  }
`;

const TodoContainer = () => {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState('');

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault(); // 양식 제거

      /*
      setTodos((todos) => {
        return todos.concat({
          id: todos.length + 1,
          title,
        });
      });
      */
      setTodos(
        produce((draft) => {
          draft.push({ id: draft.length + 1, title });
        }),
      );

      setTitle('');
    },
    [title],
  );

  const onChange = useCallback(
    (e) => setTitle(() => e.target.value.trim()),
    [],
  );

  // filter는 새로운 객체를 반환 해줌
  const onDoubleClick = useCallback(
    // (id) => setTodos((todos) => todos.filter((todo) => todo.id !== id)),
    // [],
    (id) =>
      setTodos(
        produce((draft) => {
          draft.splice(
            draft.findIndex((todo) => todo.id === id),
            1,
          );
        }),
      ),
    [],
  );

  return (
    <>
      <ContentBox>
        <TodoForm onSubmit={onSubmit} onChange={onChange} title={title} />
        <TodoList todos={todos} onDoubleClick={onDoubleClick} />
      </ContentBox>
    </>
  );
};

export default React.memo(TodoContainer);
