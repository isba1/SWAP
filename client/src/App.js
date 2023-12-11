import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Account from './Login/Account';
import Homescreen from './Home/Homescreen';
import InterestSelection from "./Login/components/InterestSelection";
import MyProfilePage from './ProfilePage/MyProfile';
import ProfilePage from './ProfilePage/OtherProfile';

function App() {
  return (
      <Router>
          <Routes>
            {/* Redirect from "/" to "/login" */}
            <Route
                path="/"
                element={<Navigate to="/login" />}
            />
              <Route path="/login" element={<Account/>} />
              <Route path="/home" element={<Homescreen/>} />
              <Route path="/newUserInterests" element={<InterestSelection/>} />
              <Route path="/myprofile" element={<MyProfilePage/>} />
              <Route path="/userprofile/:username" element={<ProfilePage/>} />
              {/* Add more routes as needed */}
          </Routes>
      </Router>
  );
}

export default App;
