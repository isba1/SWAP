import React from "react";
import "./homescreen.css";
import Offer from "../../OfferUI/Offer";
import { useNavigate } from 'react-router-dom';

function HomePosts({ FeedUserPostObject, UserID }) {
  const navigate = useNavigate();

  const handleProfileChange = async (userName) => {
    navigate(`/userprofile/${userName}`);
  }

  const firstImageLink = FeedUserPostObject.gcsUrls && FeedUserPostObject.gcsUrls.length > 0
      ? FeedUserPostObject.gcsUrls[0]
      : null;

  return (
      <div className="postcontainer">
        <div className="postrow">
          <div className="postleft">
            <button className="userbutton2" onClick={() => handleProfileChange(FeedUserPostObject.userName)}>
              {FeedUserPostObject.userName}
            </button>
              <img
                className="homedisplaypost"
                src={firstImageLink}
                alt="UserPost"
              ></img>
          </div>
          <div className="postright">
            <div className="tempcontainer">
              <div className="font">
                Name of Product: {FeedUserPostObject.postName}
              </div>
              <div className="font">
                Product Description: {FeedUserPostObject.postDescription}
              </div>
              <div className="font">
                Category: {FeedUserPostObject.postCategory}
              </div>
              <div className="font">Brand: {FeedUserPostObject.postBrand}</div>
              <div className="font">Style: {FeedUserPostObject.postStyle}</div>
              <div className="font">Size: {FeedUserPostObject.postSize}</div>
              <Offer SellerPost={FeedUserPostObject} myID={UserID} />
            </div>
          </div>
        </div>
      </div>
  );
}

export default HomePosts;
