import React from 'react';
import "./components/homescreen.css" 
import SideBar from './components/SideBar';
import HomeBar from './components/Homebar';
import HomePosts from './components/HomePosts';

function Homescreen() {
  return (<div className='homerow'>
    <div className='homecolumnleft'>
      <SideBar/>
    </div>
    <div className='homecolumnright'>
      <HomeBar/>
      <HomePosts/>
    </div>
  </div>
  );
}

export default Homescreen;
