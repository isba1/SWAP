import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Account from './Login/Account';
//import Post from "./PostUI/Post";
import Homescreen from './Home/Homescreen';

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/login" element={<Account/>} />
              <Route path="/home" element={<Homescreen/>} />
              {/* Add more routes as needed */}
          </Routes>
      </Router>
  );
}

export default App;
