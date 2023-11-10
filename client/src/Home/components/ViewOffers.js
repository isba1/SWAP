import React, { useState, useEffect } from "react";
import axios from "axios";
import "./homescreen.css";

function ViewOffersButton({ onClick }) {
  const [isPopupOpen, setPopupOpen] = useState(false);
  const [tradeOffers, setTradeOffers] = useState([]);

  useEffect(() => {
    // Fetch trade offers when the component mounts
    fetchTradeOffers();
  }, []);

  const fetchTradeOffers = async () => {
    try {
      const userID = "YOUR_USER_ID";

      // Fetch trade offers from the backend
      const response = await axios.get(
        `http://localhost:8080/offer/itemsToOffer?userID=${userID}`
      );
      setTradeOffers(response.data);
    } catch (error) {
      console.error("Error fetching trade offers:", error);
    }
  };

  const acceptOffer = async (offer) => {
    try {
      console.log("Offer Accepted:", offer);
      const response = await axios.get(
        `http://localhost:8080/offer/acceptOffer?sellerPostID=${offer.sellerPostID}&sellerUserID=${offer.sellerUserID}&buyerPostID=${offer.buyerPostID}&buyerUserID=${offer.buyerUserID}`
      );
      console.log("Accept Offer Response:", response.data);
      // After accepting, you may want to refetch the trade offers
      fetchTradeOffers();
    } catch (error) {
      console.error("Error accepting offer:", error);
    }
  };

  const declineOffer = async (offer) => {
    try {
      console.log("Offer Declined:", offer);
      const response = await axios.get(
        `http://localhost:8080/offer/declineOffer?sellerPostID=${offer.sellerPostID}&sellerUserID=${offer.sellerUserID}&buyerPostID=${offer.buyerPostID}&buyerUserID=${offer.buyerUserID}`
      );
      console.log("Decline Offer Response:", response.data);
      // After declining, you may want to refetch the trade offers
      fetchTradeOffers();
    } catch (error) {
      console.error("Error declining offer:", error);
    }
  };

  const togglePopup = () => {
    setPopupOpen(!isPopupOpen);
  };

  console.log(tradeOffers);

  return (
    <div>
      <button className="view-offers-button" onClick={togglePopup}>
        View Your Offers
      </button>

      {isPopupOpen && (
        <div className="popup">
          <div className="popup-content">
            <h2>Your Offers</h2>
            {tradeOffers.map((offer) => (
              <div key={offer.myPostID}>
                {/* Display offer details */}
                <p>Name of Your Product: {offer.myName}</p>
                <img
                  src={`data:image/png;base64, ${offer.myBase64Images[0]}`}
                  alt="Your Product"
                />
                <p>Your Product Description: {offer.myPostDescription}</p>
                <p>Your Product Category: {offer.myPostCategory}</p>
                <p>Your Product Brand: {offer.myPostBrand}</p>
                <p>Your Product Style: {offer.myPostStyle}</p>
                <p>Your Product Size: {offer.myPostSize}</p>

                <p>Name of Their Product: {offer.theirUserName}</p>
                <img
                  src={`data:image/png;base64, ${offer.theirBase64Images[0]}`}
                  alt="Their Product"
                />
                <p>Their Product Description: {offer.theirPostDescription}</p>
                <p>Their Product Category: {offer.theirPostCategory}</p>
                <p>Their Product Brand: {offer.theirPostBrand}</p>
                <p>Their Product Style: {offer.theirPostStyle}</p>
                <p>Their Product Size: {offer.theirPostSize}</p>

                <button onClick={() => acceptOffer(offer)}>Accept Offer</button>
                <button onClick={() => declineOffer(offer)}>
                  Decline Offer
                </button>
              </div>
            ))}
            <button onClick={togglePopup}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ViewOffersButton;
