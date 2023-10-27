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
        <option value="Casual">Casual</option>
        <option value="Formal">Formal</option>
        <option value="Vintage">Vintage</option>
        <option value="StreetWear">StreetWear</option>
        <option value="Bohemian">Bohemian</option>
        <option value="Preppy">Preppy</option>
        <option value="Androgynous">Androgynous</option>
        <option value="Goth">Goth</option>
        <option value="Hipster">Hipster</option>
      </select>
    </div>
  );
}

export default StyleMenu;