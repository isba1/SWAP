import React from "react";
import "./homescreen.css";

function HomePosts(){
    return (<div className="postcontainer">
            <div className="postrow">
                <div className="postleft">
                    <div className="font">Username:</div>
                    <div className="coloredsquare"></div>
                </div>
                <div className="postright">
                    <div className="tempcontainer">
                        <div className="font">Name of Product:</div>
                        <div className="font">Product Description:</div>
                        <button>Make Offer</button>
                    </div>
                </div>
        </div>
    </div>)
}


export default HomePosts