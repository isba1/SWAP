import React from "react";
import "./homescreen.css";
import { useNavigate } from 'react-router-dom';

function Offers({ tradeOffers, acceptOffer, declineOffer, toggleScroll }) {
  const navigate = useNavigate();

  const handleProfileChange = async (userName) => {
      toggleScroll();
      navigate(`/userprofile/${userName}`);
  }
  return (
    <div className="offers-container">
      <h1>Your Trade Offers</h1>
      {tradeOffers.map((offer) => (
        <div key={offer.myPostID} className="offer-item">
          <div className="your-offer">
            <h2>Your Product:</h2>
            <div className="image-container">
              <img
                className="viewofferpost"
                src={offer.myGcsUrls[0]}
                alt="Your Product"
              />
            </div>
            <p>{offer.myName}</p>
            <p>Description: {offer.myPostDescription}</p>
            <p>Category: {offer.myPostCategory}</p>
            <p>Brand: {offer.myPostBrand}</p>
            <p>Style: {offer.myPostStyle}</p>
            <p>Size: {offer.myPostSize}</p>
          </div>

          <div className="their-offer">
            <h2>{offer.theirUserName}'s Product</h2>
            <div className="image-container">
              <img
                className="viewofferpost"
                src={offer.theirGcsUrls[0]}
                alt="Their Product"
              />
            </div>
            <button className="userbutton" onClick={() => handleProfileChange(offer.theirUserName)}>Profile</button>
            <p>{offer.theirPostName}</p>
            <p>Description: {offer.theirPostDescription}</p>
            <p>Category: {offer.theirPostCategory}</p>
            <p>Brand: {offer.theirPostBrand}</p>
            <p>Style: {offer.theirPostStyle}</p>
            <p>Size: {offer.theirPostSize}</p>
          </div>

          <div className="offer-actions">
            <button
              className="accept-button"
              onClick={() => acceptOffer(offer)}
            >
              Accept
            </button>
            <button
              className="decline-button"
              onClick={() => declineOffer(offer)}
            >
              Decline
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default Offers;
