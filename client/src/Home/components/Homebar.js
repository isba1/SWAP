import React, { useState } from "react";
import axios from "axios";
import "./homescreen.css";

function HomeBar() {
  // State to store search results and user input
  const [searchResults, setSearchResults] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [selectedBrand, setSelectedBrand] = useState("all");
  const [selectedStyle, setSelectedStyle] = useState("all");
  const [selectedShirtSize, setSelectedShirtSize] = useState("all");
  const [selectedShoeSize, setSelectedShoeSize] = useState("all");
  const [selectedJacketSize, setSelectedJacketSize] = useState("all");
  const [selectedPantSize, setSelectedPantSize] = useState("all");

  // Function to handle the search when the "Search" button is clicked
  const handleSearch = async () => {
    // Create a request body with search criteria
    const searchCriteria = {
      searchInput,
      shirtSize: selectedShirtSize,
      shoeSize: selectedShoeSize,
      jacketSize: selectedJacketSize,
      pantSize: selectedPantSize,
      interestBrand: selectedBrand,
      interestStyle: selectedStyle,
    };

    try {
      // Send a POST request to the backend using Axios
      const response = await axios.post(
        "http://localhost:8080/search/searchUsers",
        searchCriteria
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
        <h3>{result.userName}</h3>
        <p>Followers: {result.followersCount}</p>
        <p>Following: {result.followingCount}</p>
      </div>
    ));
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
          />
          <button onClick={handleSearch}>Search</button>
        </div>
        <div className="filter-container">
          {/* Brands filter */}
          <select
            value={selectedBrand}
            onChange={(e) => setSelectedBrand(e.target.value)}
          >
            <option value="all">All Brands</option>
            <option value="nike">Nike</option>
            <option value="adidas">Adidas</option>
            <option value="puma">Puma</option>
            <option value="underarmor">Under Armor</option>
            <option value="ck">Calvin Klein</option>
            <option value="ralphlauren"> Ralph Lauren</option>
            <option value="levis">Levi's</option>
            <option value="tommy">Tommy Hilfiger</option>
            <option value="Patagonia">Patagonia</option>
            <option value="Lacoste">Lacoste</option>
            <option value="other">Other</option>
          </select>
          {/* Style filter */}
          <select
            value={selectedStyle}
            onChange={(e) => setSelectedStyle(e.target.value)}
          >
            <option value="all">All Styles</option>
            <option value="casual">Casual</option>
            <option value="formal">Formal</option>
            <option value="vintage">Vintage</option>
            <option value="streetwear">Streetwear</option>
            <option value="goth">Goth</option>
            <option value="other">Other</option>
          </select>
          {/* Shirt Size filter */}
          <select
            value={selectedShirtSize}
            onChange={(e) => setSelectedShirtSize(e.target.value)}
          >
            <option value="all">All Shirt Sizes</option>
            <option value="xs">XS</option>
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
            <option value="xxl">XXL</option>
          </select>
          {/* Shoe Size filter */}
          <select
            value={selectedShoeSize}
            onChange={(e) => setSelectedShoeSize(e.target.value)}
          >
            <option value="all">All Shoe Sizes</option>
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
            <option value="all">All Jacket Sizes</option>
            <option value="xs">XS</option>
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
            <option value="xxl">XXL</option>
          </select>
          {/* Pant Size filter */}
          <select
            value={selectedPantSize}
            onChange={(e) => setSelectedPantSize(e.target.value)}
          >
            <option value="all">All Pant Sizes</option>
            <option value="xs">XS</option>
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
            <option value="xxl">XXL</option>
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
