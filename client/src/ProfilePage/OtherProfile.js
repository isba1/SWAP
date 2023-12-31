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
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const postsRef = useRef(null);
  const [posts, setPosts] = useState([]);
  const [totalPosts, setTotalPosts] = useState(0);
  const [followed, setFollowed] = useState(() => {
    // Initialize followed state from localStorage, default to false if not found
    return (
      JSON.parse(localStorage.getItem(`followed_${userID}_${username}`)) ||
      false
    );
  });

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

  const handleFollowClick = async () => {
    try {
      if (!followed) {
        await axios.put(
          `http://localhost:8080/follow/add?loginUserName=${userID}&userNameToFollow=${username}`
        );
        setFollowed(true);
        localStorage.setItem(
          `followed_${userID}_${username}`,
          JSON.stringify(true)
        );
        console.log("Account followed");
      } else {
        await axios.put(
          `http://localhost:8080/follow/remove?loginUserName=${userID}&userNameToRemoveFollow=${username}`
        );
        setFollowed(false);
        localStorage.setItem(
          `followed_${userID}_${username}`,
          JSON.stringify(false)
        );
        console.log("Account unfollowed");
      }
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
          <div>
            <div className="profilehorizontalcontainer">
              <button className="homebutton" onClick={handleChange}>
                Home
              </button>
              <h1>{username}</h1>
              <p>Followers: {data.followersCount}</p>
              <p>Following: {data.followingCount}</p>
              <button
                className="followexbutton"
                onClick={handleFollowClick}
                style={{ backgroundColor: followed ? "green" : "black" }}
              >
                {followed ? "Following" : "Follow"}
              </button>
            </div>
            <div className="profilehorizontalcontainer">
              <div>Phone Number: {data.contactNumber}</div>
              <div>
                Location: {data.userCity}, {data.userState}
              </div>
            </div>
          </div>
        )}
      </div>
      {posts.slice(0, totalPosts).map((post, index) => (
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
