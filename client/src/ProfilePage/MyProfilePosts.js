import React from "react";
import "./profile.css";
import axios from "axios";

function MyProfilePosts({ PostObject }) {
    const userID = sessionStorage.getItem("userName");

    const handleDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/post/removePost?postID=${PostObject.postID}`);
            window.location.reload(true);
        } catch (error) {
            console.error(error);
        }
    }

    const firstImageLink = PostObject.gcsUrls && PostObject.gcsUrls.length > 0
        ? PostObject.gcsUrls[0]
        : null;

    return (
        <div className="profilepostcontainer">
            <div className="profilepostrow">
                <div className="profilepostleft">
                    <div className="profilefont">{userID}</div>
                    <img
                    className="profiledisplaypost"
                    src={firstImageLink}
                    alt="UserPost"
                    ></img>
                    <button className="deleteButton" onClick={handleDelete}>Delete Post</button>
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
        </div>
    );
}

export default MyProfilePosts;
