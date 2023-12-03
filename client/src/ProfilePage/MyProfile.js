import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';
import { Suspense } from 'react';
import "./profile.css";
import { useNavigate } from 'react-router-dom';
import MyFollowers from './MyFollowers';
import MyFollowering from './MyFollowing';

const LazyProfilePosts = React.lazy(() => import('./MyProfilePosts'));

function MyProfilePage (){
    const userID = sessionStorage.getItem("userName");
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

    return(<div>
            <div className='profileheader'>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div>
                    <div className='profilehorizontalcontainer'>
                    <button className='homebutton' onClick={handleChange}>Home</button>
                    <h1>{data.userName}</h1>
                    <MyFollowers place={`Followers: ${data.followersCount}`} myID={userID}/>
                    <MyFollowering place={`Following: ${data.followingCount}`} myID={userID}/>
                    </div>
                    <div className='profilehorizontalcontainer'>
                        <div>Phone Number: {data.contactNumber}</div>
                        <div>Location: {data.userCity}, {data.userState}</div>
                    </div>
                </div>
            )}
            </div>
            {myposts.slice(0, totalPosts).map((post, index) => (
                <Suspense key={index} fallback={<div>Loading post...</div>}>
                    <LazyProfilePosts PostObject={post} />
                </Suspense>
            ))}
        <div ref={postsRef}></div>
    </div>)
}

export default MyProfilePage;