import React from "react";
import "./homescreen.css";

function Offers({ tradeOffers, acceptOffer, declineOffer }) {
  return (
    <div className="offers-container">
      <h1>Your Trade Offers</h1>
      {tradeOffers.map((offer) => (
        <div key={offer.myPostID} className="offer-item">
          <div className="your-offer">
            <h2>Your Product:</h2>
            <div className="image-container">
              <img
                src={`data:image/png;base64, ${offer.myBase64Images[0]}`}
                alt="Your Product"
              />
            </div>
            <p>Description: {offer.myPostDescription}</p>
            <p>Category: {offer.myPostCategory}</p>
            <p> Brand: {offer.myPostBrand}</p>
            <p>Style: {offer.myPostStyle}</p>
            <p>Size: {offer.myPostSize}</p>
          </div>

          <div className="their-offer">
            <h2>{offer.theirUserName}'s Product:</h2>
            <div className="image-container">
              <img
                src={`data:image/png;base64, ${offer.theirBase64Images[0]}`}
                alt="Their Product"
              />
            </div>
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
