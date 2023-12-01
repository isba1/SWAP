import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import { Suspense } from "react";
import "./profile.css";
import { useNavigate, useParams } from "react-router-dom";

const LazyProfilePosts = React.lazy(() => import("./OtherProfilePosts"));

function ProfilePage() {
  const navigate = useNavigate();
  const userID = sessionStorage.getItem("userName");
  const { username } = useParams();
  if (userID === username) {
    navigate("/myprofile");
  }
  const [loadedPosts, setLoadedPosts] = useState(0);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const postsRef = useRef(null);
  const [posts, setPosts] = useState([]);
  const [totalPosts, setTotalPosts] = useState(0);
  const [followed, setFollowed] = useState(false);

  const fetchFollowStatus = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/profile/singleFollower?userID=${userID}`
      );
      setFollowed(response.data.followed);
    } catch (error) {
      console.error("Error fetching follow status:", error);
    }
  };

  const handleChange = async (event) => {
    navigate("/home");
  };

  useEffect(() => {
    if (data === null) {
      axios
        .get(`http://localhost:8080/search/singleUser?userID=${username}`)
        .then((response) => {
          setData(response.data);
          setPosts(response.data.userPosts);
          setTotalPosts(response.data.userPosts.length);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
          setLoading(false);
        });
    }
  }, [data, username]);

  useEffect(() => {
    const loadMorePosts = () => {
      if (loadedPosts < posts.length) {
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
  }, [loadedPosts, totalPosts, posts]);

  const handleFollowClick = async () => {
    try {
      setFollowed(!followed);

      // Use the appropriate endpoint based on follow/unfollow action
      if (!followed) {
        await axios.get(
          `http://localhost:8080/follow/add?loginUserName=${userID}&userNameToFollow=${username}`
        );
      } else {
        await axios.get(
          `http://localhost:8080/follow/remove?loginUserName=${userID}&userNameToRemoveFollow=${username}`
        );
      }

      // Update follow status after API call
      fetchFollowStatus();
    } catch (error) {
      console.error("Error toggling follow status:", error);
      setFollowed(!followed); // Reset the state to previous value on error
    }
  };

  return (
    <div>
      <div className="profileheader">
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className="profilehorizontalcontainer">
            <button className="homebutton" onClick={handleChange}>
              Home
            </button>
            <h1>{username}</h1>
            <p>Followers: {data.followersCount}</p>
            <p>Following: {data.followingCount}</p>
            <button
              className="follow"
              onClick={handleFollowClick}
              style={{ backgroundColor: followed ? "green" : "black" }}
            >
              {followed ? "Following" : "Follow"}
            </button>
          </div>
        )}
      </div>
      {posts.slice(0, loadedPosts).map((post, index) => (
        <Suspense key={index} fallback={<div>Loading post...</div>}>
          <LazyProfilePosts
            PostObject={post}
            UserID={userID}
            SellerId={username}
          />
        </Suspense>
      ))}
      <div ref={postsRef}></div>
    </div>
  );
}

export default ProfilePage;
