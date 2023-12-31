import React from "react";
import "./profile.css";
import OtherProfileOffer from "../OfferUI/OtherProfileOffer";

function OtherProfilePosts({PostObject, UserID, SellerId}){
    //add thing for useEffect to get the FeedPost
    const firstImageLink = PostObject.gcsUrls && PostObject.gcsUrls.length > 0
    ? PostObject.gcsUrls[0]
    : null;

    return (<div className="profilepostcontainer">
            <div className="profilepostrow">
                <div className="profilepostleft">
                    <div className="profilefont">{PostObject.userName}</div>
                    <img
                    className="profiledisplaypost"
                    src={firstImageLink}
                    alt="UserPost"
                    ></img>
                </div>
                <div className="profilepostright">
                    <div className="profiletempcontainer">
                        <div className="font">Name of Product: {PostObject.name}</div>
                        <div className="font">Product Description: {PostObject.postDescription}</div>
                        <div className="font">Category: {PostObject.postCategory}</div>
                        <div className="font">Brand: {PostObject.postBrand}</div>
                        <div className="font">Style: {PostObject.postStyle}</div>
                        <div className="font">Size: {PostObject.postSize}</div>
                        <OtherProfileOffer SellerPost={PostObject} myID={UserID} sellerid={SellerId}/>
                    </div>
                </div>
        </div>
    </div>)
}


export default OtherProfilePosts;