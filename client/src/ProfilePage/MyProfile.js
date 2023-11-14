import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';
import { Suspense } from 'react';
import "./profile.css";
import { useNavigate } from 'react-router-dom';

const LazyProfilePosts = React.lazy(() => import('./MyProfilePosts'));

function MyProfilePage (){
    const userID = sessionStorage.getItem("userID");
    const [loadedPosts, setLoadedPosts] = useState(0);
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const postsRef = useRef(null);
    const [myposts, setMyPosts] = useState([]);
    const [totalPosts, setTotalPosts] = useState(0);
    const navigate = useNavigate();

    const handleChange = async (event) => {
        navigate('/home');
    }

    useEffect(() =>{
        if (data === null){
            axios.get(`http://localhost:8080/profile/myProfile?UserID=${userID}`)
            .then((response) => {
                setData(response.data);
                setMyPosts(response.data.userPosts);
                setTotalPosts(response.data.userPosts.length);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
                setLoading(false);
            });
        }
    }, [data, userID]);

    useEffect(() => {
        const loadMorePosts = () =>{
            if (loadedPosts < myposts.length){
                setLoadedPosts((prevLoadedPosts) => prevLoadedPosts + 1);
            }
        };
        const observer = new IntersectionObserver((entries) => {
          if (entries[0].isIntersecting) {
            //console.log('Element is intersecting');
            loadMorePosts();
          } else {
            //console.log('Element is not intersecting');
          }
        });
    
        observer.observe(postsRef.current);
        return () => observer.disconnect();
      }, [loadedPosts, totalPosts, myposts]);

    return(<div>
            <div className='profileheader'>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div className='profilehorizontalcontainer'>
                <button className='homebutton' onClick={handleChange}>Home</button>
                <h1>{data.userName}</h1>
                <p>Followers: {data.followersCount}</p>
                <p>Following: {data.followingCount}</p>
                </div>
            )}
            </div>
            {myposts.slice(0, loadedPosts).map((post, index) => (
                <Suspense key={index} fallback={<div>Loading post...</div>}>
                    <LazyProfilePosts PostObject={post} />
                </Suspense>
            ))}
        <div ref={postsRef}></div>
    </div>)
}

export default MyProfilePage;