import React, { useState } from "react";
import "./homescreen.css";

function HomeBar() {
  // State to store search results and user input
  const [searchResults, setSearchResults] = useState([]);
  const [searchInput, setSearchInput] = useState("");

  // Function to handle the search when the "Search" button is clicked
  const handleSearch = () => {
    // Send a request to the backend to perform the search
    fetch("/search", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ searchInput }), // Send user input to the backend
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
            placeholder="&#x1F50D; Start typing to search for other profiles..."
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
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
