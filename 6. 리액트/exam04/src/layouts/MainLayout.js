import React from 'react';
import Header from '../outlines/Header';
import Footer from '../outlines/Footer';
import { Outlet } from 'react-router-dom';

const MainLayout = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </>
  );
};

export default React.memo(MainLayout);
