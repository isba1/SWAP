import React, { useState, useRef, useEffect } from 'react';
import { Suspense } from 'react';
import "./components/homescreen.css";
import SideBar from './components/SideBar';
import HomeBar from './components/Homebar';
import axios from 'axios'

const LazyHomePosts = React.lazy(() => import('./components/HomePosts'));

const Homescreen = () => {
  const [loadedPosts, setLoadedPosts] = useState(0);
  const [recommendedPosts, setRecommendedPosts] = useState([]);
  const postsRef = useRef(null);

  //this will be the myuser's userid/token
  const userNAME = sessionStorage.getItem("userName");
  //this will be a FeedUserPost object

  useEffect(() => {
    axios.get(`/home?userID=${userNAME}`) //  API endpoint goes here
      .then(response => {
        setRecommendedPosts(response.data);
      })
      .catch(error => {
        console.error('Error fetching recommended posts:', error);
      });

    const observer = new IntersectionObserver((entries) => {
      if (entries[0].isIntersecting) {
        setLoadedPosts((prevLoadedPosts) => prevLoadedPosts + 1);
      }
    });

    observer.observe(postsRef.current);
    return () => observer.disconnect();
  }, [userNAME]);

  return (
    <div className='homerow'>
      <div className='homecolumnleft'>
        <SideBar />
      </div>
      <div className='homecolumnright'>
        <HomeBar />
        {recommendedPosts.slice(0, loadedPosts).map((post, index) => (
          <div key={index}>
            <Suspense fallback={<div>Loading post...</div>}>
              <LazyHomePosts FeedUserPostObject={post} UserNAME={userNAME} />
            </Suspense>
            <div className="post-details">
              <h3>{post.userName}</h3>
              <p>{post.postDescription}</p>
              <p>Category: {post.postCategory}</p>
              <p>Brand: {post.postBrand}</p>
              <p>Style: {post.postStyle}</p>
              <p>Size: {post.postSize}</p>
            </div>
          </div>
        ))}
        <div ref={postsRef}></div>
      </div>
    </div>
  );
};

export default Homescreen;