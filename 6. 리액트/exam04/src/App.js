import { Routes, Route } from 'react-router-dom';
// import Home from './pages/Home';
// import About from './pages/About';
// import NotFound from './pages/NotFound';
import loadable from '@loadable/component';
import MainLayout from './layouts/MainLayout';
const Home = loadable(() => import('./pages/Home'));
const About = loadable(() => import('./pages/About'));
const NotFound = loadable(() => import('./pages/NotFound'));

const App = () => {
  return (
    // 주소와 매칭 될 수 있게 매칭
    // 여러개일 때 지연로딩 사용
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<Home />} />
        <Route path="/about" element={<About />} />
      </Route>
      {/* 상단 체크 후 없는 경로면 이쪽으로 유입됨 */}
      <Route path="*" element={<NotFound />} />

      {/* <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} /> */}

      {/* /profile/:username */}
      {/* <Route path="/profile" element={<ProfileHeader />}>
        <Route path=":username" element={<Profile />} />
      </Route> */}
    </Routes>
  );
};

export default App;
