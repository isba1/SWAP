import React, { useState, useRef, useEffect } from 'react';
import { Suspense } from 'react';
import "./components/homescreen.css";
import SideBar from './components/SideBar';
import HomeBar from './components/Homebar';

const LazyHomePosts = React.lazy(() => import('./components/HomePosts'));

const Homescreen = () => {
  const [loadedPosts, setLoadedPosts] = useState(0);
  const postsRef = useRef(null);

  //this will be the myuser's userid/token
  const userID = sessionStorage.getItem("userID");
  //this will be a FeedUserPost object
  
  const FeedUserTest = {
    postID: '',
    userID: '',
    userName: 'ExampleUser',
    name: 'Gray Sweatpants',
    postDescription: 'Gray Sweapants',
    postCategory: 'EXAMPLE',
    postBrand: 'EXAMPLE',
    postStyle: 'EXAMPLE',
    postSize: 'EXAMPLE',
  }
  
  useEffect(() => {
    const observer = new IntersectionObserver((entries) => {
      if (entries[0].isIntersecting) {
        //console.log('Element is intersecting');
        setLoadedPosts((prevLoadedPosts) => prevLoadedPosts + 1);
      } else {
        //console.log('Element is not intersecting');
      }
    });

    observer.observe(postsRef.current);
    return () => observer.disconnect();
  }, []);

  return (
    <div className='homerow'>
      <div className='homecolumnleft'>
        <SideBar />
      </div>
      <div className='homecolumnright'>
        <HomeBar />
        {Array.from({ length: loadedPosts }).map((_, index) => (
          <Suspense key={index} fallback={<div>Loading post...</div>}>
            <LazyHomePosts FeedUserPostObject={FeedUserTest} UserID={userID}/>
          </Suspense>
        ))}
        <div ref={postsRef}></div>
      </div>
    </div>
  );
};

export default Homescreen;