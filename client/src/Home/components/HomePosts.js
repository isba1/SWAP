import React from "react";
import "./homescreen.css";
import Offer from "../../OfferUI/Offer";
//import axios from "axios";
//import { useEffect, useState } from 'react';

function HomePosts({ FeedUserPostObject, UserID }) {
  //add thing for useEffect to get the FeedPost
  return (
    <div className="postcontainer">
      <div className="postrow">
        <div className="postleft">
          <div className="font">{FeedUserPostObject.userName}</div>
          <div className="coloredsquare"></div>
        </div>
        <div className="postright">
          <div className="tempcontainer">
            <div className="font">
              Name of Product: {FeedUserPostObject.name}
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
