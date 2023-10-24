import React from "react";
import "./homescreen.css";
import Post from "../../PostUI/Post"

function SideBar(){
    return(<div>
        <h1 className="homeheader">SWAP</h1>
        <h2 className="hometitle">Explore</h2>
            <div className="homesidecontainer">
                <button className="homeexbutton">Tops</button>
                <button className="homeexbutton">Bottoms</button>
                <button className="homeexbutton">Outerwear</button>
                <button className="homeexbutton">Shoes</button>
                <button className="homeexbutton">Accessories</button>
                <Post/>            
            </div>
    </div>)
}

export default SideBar;