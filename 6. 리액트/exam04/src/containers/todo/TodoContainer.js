import React from 'react';
import TodoForm from '../../components/todo/TodoForm';
import TodoList from '../../components/todo/TodoList';
import styled from 'styled-components';

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

const todos = [
  {
    id: 1,
    title: '할일1',
  },
  {
    id: 2,
    title: '할일2',
  },
];

const TodoContainer = () => {
  return (
    <>
      <ContentBox>
        <TodoForm />
        <TodoList todos={todos} />
      </ContentBox>
    </>
  );
};

export default React.memo(TodoContainer);
