import React from "react";
import "./homescreen.css";
import Post from "../../PostUI/Post";
import { useNavigate } from 'react-router-dom';

function SideBar({setFilteredPosts}){
    const navigate = useNavigate();
    const handleChange = async (event) => {
        navigate('/myprofile');
    }

    const clearFilteredPosts = () =>{
        setFilteredPosts([]);
        window.location.reload(true);
      }

    return(<div>
        <h1 className="homeheader">SWAP</h1>
        <button onClick={handleChange}>My Profile</button>
        <h2 className="hometitle">Explore</h2>
            <div className="homesidecontainer">
                <button className="homeexbutton" onClick={clearFilteredPosts}>Recommended</button> 
                <button className="homeexbutton">Tops</button>
                <button className="homeexbutton">Bottoms</button>
                <button className="homeexbutton">Outerwear</button>
                <button className="homeexbutton">Shoes</button>
                <button className="homeexbutton">Accessories</button>            
            </div>
        <Post/>
    </div>)
}

export default SideBar;