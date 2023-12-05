import React from "react";
import "./homescreen.css";
import Post from "../../PostUI/Post";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function SideBar({setFilteredPosts}){
    const userID = sessionStorage.getItem("userName");
    const navigate = useNavigate();
    const handleChange = async (event) => {
        navigate('/myprofile');
    }

    const clearFilteredPosts = () =>{
        setFilteredPosts(null);
    }

    const handleTopsButtonClicked = async () =>{
        axios.get(`http://localhost:8080/home/exploreTops?userID=${userID}`)
        .then((response) => {
            setFilteredPosts(response.data);
        })
        .catch((error) => {
          console.error(error)
        })
    }
    const handleBottomsButtonClicked = async () =>{
        axios.get(`http://localhost:8080/home/exploreBottoms?userID=${userID}`)
        .then((response) => {
            setFilteredPosts(response.data);
        })
        .catch((error) => {
          console.error(error);
        })
    }
    const handleOuterWearButtonClicked = async ()=>{
        axios.get(`http://localhost:8080/home/exploreOuterwear?userID=${userID}`)
        .then((response) => {
            setFilteredPosts(response.data);
        })
        .catch((error) => {
          console.error(error);
        })
    }
    const handleShoesButtonClicked = async () =>{
        axios.get(`http://localhost:8080/home/exploreShoes?userID=${userID}`)
        .then((response) => {
            setFilteredPosts(response.data);
        })
        .catch((error) => {
          console.error(error);
        })
    }
    const handleAccessoriesButtonClicked = async () =>{
        axios.get(`http://localhost:8080/home/exploreAccessories?userID=${userID}`)
        .then((response) => {
            setFilteredPosts(response.data);
        })
        .catch((error) => {
          console.error(error);
        })
    }



    return(<div>
        <h1 className="homeheader">SWAP</h1>
        <button onClick={handleChange}>My Profile</button>
        <h2 className="hometitle">Explore</h2>
            <div className="homesidecontainer">
                <button className="homeexbutton" onClick={clearFilteredPosts}>Recommended</button> 
                <button className="homeexbutton" onClick={handleTopsButtonClicked}>Tops</button>
                <button className="homeexbutton" onClick={handleBottomsButtonClicked}>Bottoms</button>
                <button className="homeexbutton" onClick={handleOuterWearButtonClicked}>Outerwear</button>
                <button className="homeexbutton" onClick={handleShoesButtonClicked}>Shoes</button>
                <button className="homeexbutton" onClick={handleAccessoriesButtonClicked}>Accessories</button>            
            </div>
        <Post/>
    </div>)
}

export default SideBar;