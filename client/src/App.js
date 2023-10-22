import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Account from './Login/Account';
import Post from "./PostUI/Post";
import LandingPage from "./Home/LandingPage";

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<Account/>} />
              <Route path="/login" element={<Account/>} />
              <Route path="/post" component={<Post/>} />
              <Route path="/landingPageTest" component={<LandingPage/>} />
              {/* Add more routes as needed */}
          </Routes>
      </Router>
  );
}

export default App;
