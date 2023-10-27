import React from 'react';
import "./Post.css"

function SizeMenu({selectedOption, setSelectedOption}) {
  const handleSelectChange = (e) => {
    setSelectedOption(e.target.value);
  }
  //add as many as needed/wanted
  return (
    <div>
      <select value={selectedOption} onChange={handleSelectChange}>
        <option value="XS">XS</option>
        <option value="S">Small</option>
        <option value="M">Medium</option>
        <option value="L">Large</option>
        <option value="XL">Xtra Large</option>
      </select>
    </div>
  );
}

export default SizeMenu;