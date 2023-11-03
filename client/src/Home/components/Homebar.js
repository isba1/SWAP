import React, { useState } from "react";
import "./homescreen.css";

function HomeBar() {
  // State to store search results and user input
  const [searchResults, setSearchResults] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [selectedBrand, setSelectedBrand] = useState("all");
  const [selectedSize, setSelectedSize] = useState("all");
  const [selectedStyle, setSelectedStyle] = useState("all");

  // Function to handle the search when the "Search" button is clicked
  const handleSearch = () => {
    // Send a request to the backend to perform the search
    fetch("/search", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        searchInput,
        brand: selectedBrand,
        size: selectedSize,
        style: selectedStyle,
      }), // Send user input to the backend
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the received data and update search results
        setSearchResults(data);
      })
      .catch((error) => console.error("Error:", error));
  };

  // Function to render search results
  const displaySearchResults = () => {
    // Maps over the searchResults array and generates a list of search results to be displayed in the UI.
    return searchResults.map((result) => (
      <div key={result.id} className="search-result">
        {result.username}
        {/* You can display other user information here */}
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
        <div className="filters">
          {/* Brands filter */}
          <select
            value={selectedBrand}
            onChange={(e) => setSelectedBrand(e.target.value)}
          >
            <option value="all">All Brands</option>
            <option value="nike">Nike</option>
            <option value="adidas">Adidas</option>
            <option value="levis">Levi's</option>
            <option value="luckybrand">Lucky Brand</option>
            <option value="ck">Calvin Klein</option>
            <option value="polo">Polo Ralph Lauren</option>
          </select>
          {/* Size filter */}
          <select
            value={selectedSize}
            onChange={(e) => setSelectedSize(e.target.value)}
          >
            <option value="all">All Sizes</option>
            <option value="xs">XS</option>
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
            <option value="xxl">XXL</option>
            <option value="xxxl">XXXL</option>
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
            <option value="bohemian">Bohemian</option>
            <option value="preppy">Preppy</option>
            <option value="athleisure">Athleisure</option>
            <option value="gothic">Gothic</option>
            <option value="hipster">Hipster</option>
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
