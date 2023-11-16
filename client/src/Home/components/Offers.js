import React from "react";
import "./homescreen.css";

function Offers({ tradeOffers, acceptOffer, declineOffer }) {
  return (
    <div className="offers-container">
      <h2>Your Offers</h2>
      {tradeOffers.map((offer) => (
        <div key={offer.myPostID} className="offer-item">
          <div className="your-offer">
            <p>Name of Your Product: {offer.myName}</p>
            <div className="image-container">
              <img
                src={`data:image/png;base64, ${offer.myBase64Images[0]}`}
                alt="Your Product"
              />
            </div>
            <p>Your Product Description: {offer.myPostDescription}</p>
            <p>Your Product Category: {offer.myPostCategory}</p>
            <p>Your Product Brand: {offer.myPostBrand}</p>
            <p>Your Product Style: {offer.myPostStyle}</p>
            <p>Your Product Size: {offer.myPostSize}</p>
          </div>

          <div className="their-offer">
            <p>Name of Their Product: {offer.theirUserName}</p>
            <div className="image-container">
              <img
                src={`data:image/png;base64, ${offer.theirBase64Images[0]}`}
                alt="Their Product"
              />
            </div>
            <p>Their Product Description: {offer.theirPostDescription}</p>
            <p>Their Product Category: {offer.theirPostCategory}</p>
            <p>Their Product Brand: {offer.theirPostBrand}</p>
            <p>Their Product Style: {offer.theirPostStyle}</p>
            <p>Their Product Size: {offer.theirPostSize}</p>
          </div>

          <div className="offer-actions">
            <button className="button" onClick={() => acceptOffer(offer)}>
              Accept
            </button>
            <button className="button" onClick={() => declineOffer(offer)}>
              Decline
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default Offers;
