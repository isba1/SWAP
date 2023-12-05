import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
import "./homescreen.css";
import ViewOffersButton from "./ViewOffers";
import { useNavigate } from "react-router-dom";
import { Notifications } from "./Notifications/Notifications";

function HomeBar() {
  const userID = sessionStorage.getItem("userName");

  // State to store search results and user input
  const [searchResults, setSearchResults] = useState([]);
  const [searchInput, setSearchInput] = useState(null);
  const [selectedBrand, setSelectedBrand] = useState(null);
  const [selectedStyle, setSelectedStyle] = useState(null);
  const [selectedShirtSize, setSelectedShirtSize] = useState(null);
  const [selectedShoeSize, setSelectedShoeSize] = useState(null);
  const [selectedJacketSize, setSelectedJacketSize] = useState(null);
  const [selectedPantSize, setSelectedPantSize] = useState(null);
  const [isPopupOpen, setPopupOpen] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const deleteFirstNotification = useCallback(() => {
    setNotifications(notifications.slice(1));
  }, [notifications]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/profile/getNotifications?userID=${userID}`)
      .then((response) => {
        setNotifications(response.data);
      })
      .catch((error) => {
        console.error(`Error fetching notifications: `, error);
      });
  }, []);

  const navigate = useNavigate();

  const handleProfileChange = async (userName) => {
    navigate(`/userprofile/${userName}`);
  };

  // Function to handle the search when the "Search" button is clicked

  // check if there's functionality for if the search result is empty
  const handleSearch = async () => {
    if (searchInput === "") {
      setSearchInput(null);
      return;
    }
    // Create a request body with search criteria
    // const searchCriteria = {
    //   userName: searchInput,
    //   shirtSize: selectedShirtSize,
    //   shoeSize: selectedShoeSize,
    //   jacketSize: selectedJacketSize,
    //   pantSize: selectedPantSize,
    //   interestBrand: selectedBrand,
    //   interestStyle: selectedStyle,
    // };

    try {
      // Send a GET request to the backend using Axios
      const response = await axios.get(
        `http://localhost:8080/search/searchUsers?userName=${searchInput}&shirtSize=${selectedShirtSize}&shoeSize=
          ${selectedShoeSize}&jacketSize=${selectedJacketSize}&pantSize=${selectedPantSize}&interestBrand=${selectedBrand}
          &interestStyle=${selectedStyle}`
      );

      // Handle the received data and update search results
      setSearchResults(response.data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  // Function to render search results
  const displaySearchResults = () => {
    // Maps over the searchResults array and generates a list of search results to be displayed in the UI.
    return searchResults.map((result) => (
      <div key={result.userID} className="search-result">
        <button
          className="searchexbutton"
          onClick={() => handleProfileChange(result.userName)}
        >
          {result.userName}
        </button>
        <p className="smallspace">Followers: {result.followersCount}</p>
        <p className="smallspace">Following: {result.followingCount}</p>
      </div>
    ));
  };

  const togglePopup = () => {
    setPopupOpen(!isPopupOpen);
  };

  return (
    <div>
      <header>
        <div className="search-bar">
          <input
            id="search"
            type="search"
            placeholder="&#x1F50D;Search for other profiles with similar interests..."
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
            className="searchbarinput"
          />
          <button className="searchbutton" onClick={handleSearch}>
            Search
          </button>
          <ViewOffersButton onClick={togglePopup} />
          <Notifications
            notifications={notifications}
            deleteFirstNotification={deleteFirstNotification}
          />
        </div>

        <div className="filter-container">
          {/* Brands filter */}
          <select
            value={selectedBrand}
            onChange={(e) => setSelectedBrand(e.target.value)}
          >
            <option value="null">Choose Brand</option>
            <option value="Nike">Nike</option>
            <option value="Adidas">Adidas</option>
            <option value="Puma">Puma</option>
            <option value="Under Armor">Under Armor</option>
            <option value="Calvin Klein">Calvin Klein</option>
            <option value="Ralph Lauren"> Ralph Lauren</option>
            <option value="Levi's">Levi's</option>
            <option value="Tommy Hilfiger">Tommy Hilfiger</option>
            <option value="Patagonia">Patagonia</option>
            <option value="Lacoste">Lacoste</option>
            <option value="Other">Other</option>
          </select>
          {/* Style filter */}
          <select
            value={selectedStyle}
            onChange={(e) => setSelectedStyle(e.target.value)}
          >
            <option value="null">Choose Style</option>
            <option value="Casual">Casual</option>
            <option value="Formal">Formal</option>
            <option value="Vintage">Vintage</option>
            <option value="Streetware">Streetware</option>
            <option value="Goth">Goth</option>
            <option value="Other">Other</option>
          </select>
          {/* Shirt Size filter */}
          <select
            value={selectedShirtSize}
            onChange={(e) => setSelectedShirtSize(e.target.value)}
          >
            <option value="null">Choose Shirt Size</option>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
            <option value="XXL">XXL</option>
          </select>
          {/* Shoe Size filter */}
          <select
            value={selectedShoeSize}
            onChange={(e) => setSelectedShoeSize(e.target.value)}
          >
            <option value="null">Choose Shoe Size</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="7">8</option>
            <option value="7">9</option>
            <option value="other">other</option>
          </select>
          {/* Jacket Size filter */}
          <select
            value={selectedJacketSize}
            onChange={(e) => setSelectedJacketSize(e.target.value)}
          >
            <option value="null">Choose Jacket Size</option>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
            <option value="XXL">XXL</option>
          </select>
          {/* Pant Size filter */}
          <select
            value={selectedPantSize}
            onChange={(e) => setSelectedPantSize(e.target.value)}
          >
            <option value="null">Choose Pant Size</option>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
            <option value="XXL">XXL</option>
          </select>
        </div>
      </header>
      <main className="container">
        <div className="search-display">{displaySearchResults()}</div>
        <div className="posts-container"></div>
      </main>{" "}
    </div>
  );
}
export default HomeBar;
