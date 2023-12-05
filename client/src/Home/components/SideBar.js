import React from "react";
import "./homescreen.css";
import Post from "../../PostUI/Post";
import { useNavigate } from "react-router-dom";

function SideBar() {
  const navigate = useNavigate();
  const handleChange = async (event) => {
    navigate("/myprofile");
  };
  return (
    <div>
      <h1 className="homeheader">SWAP</h1>
      <button className="sidebarbutton" onClick={handleChange}>
        My Profile
      </button>
      <Post className="postbutton" />
    </div>
  );
}

export default SideBar;
