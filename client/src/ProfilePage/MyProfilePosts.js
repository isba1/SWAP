import React from "react";
import "./profile.css";

function MyProfilePosts({PostObject}){
    //add thing for useEffect to get the FeedPost
    return (<div className="profilepostcontainer">
            <div className="profilepostrow">
                <div className="profilepostleft">
                    <div className="profilefont">{PostObject.userName}</div>
                    <div className="profilecoloredsquare"></div>
                </div>
                <div className="profilepostright">
                    <div className="profiletempcontainer">
                        <div className="font">Name of Product: {PostObject.name}</div>
                        <div className="font">Product Description: {PostObject.postDescription}</div>
                        <div className="font">Category: {PostObject.postCategory}</div>
                        <div className="font">Brand: {PostObject.postBrand}</div>
                        <div className="font">Style: {PostObject.postStyle}</div>
                        <div className="font">Size: {PostObject.postSize}</div>
                    </div>
                </div>
        </div>
    </div>)
}


export default MyProfilePosts;