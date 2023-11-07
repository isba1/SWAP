import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Account from './Login/Account';
import Homescreen from './Home/Homescreen';
import InterestSelection from "./Login/components/InterestSelection";
import ProfilePage from './ProfilePage/Profile';

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/login" element={<Account/>} />
              <Route path="/home" element={<Homescreen/>} />
              <Route path="/newUserInterests" element={<InterestSelection/>} />
              <Route path ="/myprofile" element={<ProfilePage/>} />
              {/* Add more routes as needed */}
          </Routes>
      </Router>
  );
}

export default App;
