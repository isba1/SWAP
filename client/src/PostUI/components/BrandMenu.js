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
      <option value="" disabled>Choose a Brand</option>
        <option value="Nike">Nike</option>
        <option value="Adidas">Adidas</option>
        <option value="Puma">Puma</option>
        <option value="Under Armor">Under Armor</option>
        <option value="Ralph Lauren">Ralph Lauren</option>
        <option value="Tommy Hilfiger">Tommy Hilfiger</option>
        <option value="Patagonia">Patagonia</option>
        <option value="Lacoste">Lacoste</option>
        <option value="Levi's">Levi's</option>
        <option value="Calvin Klein">Calvin Klein</option>
        <option value="Other">Other</option>
      </select>
    </div>
  );
}

export default BrandMenu;