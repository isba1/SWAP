import React, { useState, useEffect } from "react";
import axios from "axios";
import "./homescreen.css";

const Offers = React.lazy(() => import("./Offers")); // Import the Offers container

function ViewOffersButton({ onClick }) {
  const userID = sessionStorage.getItem("userName");
  const [isPopupOpen, setPopupOpen] = useState(false);
  const [tradeOffers, setTradeOffers] = useState([]);

  useEffect(() => {
    // Fetch trade offers when the component mounts
    fetchTradeOffers();
  }, []);

  const fetchTradeOffers = async () => {
    try {
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

  // Simulated test data similar to FeedUserTest for testing ViewOffersButton component
  const TestOffer = {
    myPostID: "123",
    myName: "Test Product",
    myBase64Images: ["base64data1", "base64data2"],
    myPostDescription: "Test Product Description",
    myPostCategory: "Test Category",
    myPostBrand: "Test Brand",
    myPostStyle: "Test Style",
    myPostSize: "Test Size",
    theirUserName: "Their Username",
    theirBase64Images: ["theirBase64data1", "theirBase64data2"],
    theirPostDescription: "Their Product Description",
    theirPostCategory: "Their Category",
    theirPostBrand: "Their Brand",
    theirPostStyle: "Their Style",
    theirPostSize: "Their Size",
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
                tradeOffers={[TestOffer]}
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
