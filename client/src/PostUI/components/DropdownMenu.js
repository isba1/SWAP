import React from 'react';
import "./Post.css"

function DropdownMenu({selectedOption, setSelectedOption}) {
  const handleSelectChange = (e) => {
    setSelectedOption(e.target.value);
  }

  return (
    <div>
      <select value={selectedOption} onChange={handleSelectChange}>
        <option value="Tops">Tops</option>
        <option value="Bottoms">Bottoms</option>
        <option value="Outerwear">Outerwear</option>
        <option value="Shoes">Shoes</option>
        <option value="Accessories">Accessories</option>
      </select>
    </div>
  );
}

export default DropdownMenu;
