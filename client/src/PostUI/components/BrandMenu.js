import React from 'react';
import "./Post.css"

function BrandMenu({selectedOption, setSelectedOption}) {
  const handleSelectChange = (e) => {
    setSelectedOption(e.target.value);
  }
  //add as many as needed/wanted
  return (
    <div>
      <select value={selectedOption} onChange={handleSelectChange}>
        <option value="Nike">Nike</option>
        <option value="Adidas">Adidas</option>
        <option value="Levi's">Levi's</option>
        <option value="Calvin Klein">Calvin Klein</option>
        <option value="Other">Other</option>
      </select>
    </div>
  );
}

export default BrandMenu;