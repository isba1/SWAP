import React from 'react';
import "./Post.css"

function StyleMenu({selectedOption, setSelectedOption}) {
  const handleSelectChange = (e) => {
    setSelectedOption(e.target.value);
  }
  //add as many as needed/wanted
  return (
    <div>
      <select value={selectedOption} onChange={handleSelectChange}>
      <option value="" disabled>Choose a Style</option>
        <option value="Casual">Casual</option>
        <option value="Formal">Formal</option>
        <option value="Vintage">Vintage</option>
        <option value="StreetWear">StreetWear</option>
        <option value="Goth">Goth</option>
        <option value="Other">Other</option>
      </select>
    </div>
  );
}

export default StyleMenu;