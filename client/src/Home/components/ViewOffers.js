import React, { useState, useEffect } from "react";
import axios from "axios";
import "./homescreen.css";

const Offers = React.lazy(() => import("./Offers")); // Import the Offers container

function ViewOffersButton({ onClick }) {
  const [isPopupOpen, setPopupOpen] = useState(false);
  const [tradeOffers, setTradeOffers] = useState([]);
  const userID = sessionStorage.getItem("userName");

  useEffect(() => {
    // Fetch trade offers when the component mounts
    fetchTradeOffers();
  }, []);

  const fetchTradeOffers = async () => {
    try {

      // Fetch trade offers from the backend
      const response = await axios.get(
        `http://localhost:8080/profile/offersReceived?UserID=${userID}`
      );
      setTradeOffers(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Error fetching trade offers:", error);
    }
  };

  const acceptOffer = async (offer) => {
    try {
      console.log("Offer Accepted:", offer);
      const response = await axios.get(
        `http://localhost:8080/offer/acceptOffer?sellerPostID=${offer.theirPostID}&sellerUserID=${offer.theirUserName}&buyerPostID=${offer.myPostID}&buyerUserID=${userID}`
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
        `http://localhost:8080/offer/declineOffer?sellerPostID=${offer.theirPostID}&sellerUserID=${offer.theirUserName}&buyerPostID=${offer.myPostID}&buyerUserID=${userID}`
      );
      console.log("Decline Offer Response:", response.data);
      // After declining, you may want to refetch the trade offers
      fetchTradeOffers();
    } catch (error) {
      console.error("Error declining offer:", error);
    }
  };

  // To toggle scrolling on the main page
  const toggleScroll = () => {
    document.body.style.overflow = isPopupOpen ? "auto" : "hidden";
  };

  const togglePopup = () => {
    setPopupOpen(!isPopupOpen);
    toggleScroll(); // Toggle scrolling on the main page
  };

  const closePopup = () => {
    setPopupOpen(false);
    toggleScroll(); // Toggle scrolling on the main page
  };


  return (
    <div>
      <button className="view-offers-button" onClick={togglePopup}>
        View Your Offers
      </button>

      {isPopupOpen && (
        <div className="popup">
          <div className="popup-content">
            <React.Suspense fallback={<div>Loading test offer...</div>}>
              <Offers
                tradeOffers={tradeOffers}
                acceptOffer={acceptOffer}
                declineOffer={declineOffer}
              />
            </React.Suspense>
            <button className="close-button" onClick={closePopup}>
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ViewOffersButton;
