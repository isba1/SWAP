import React from "react";

const ShoeSizeMenu = ({selectedOption, setSelectedOption}) => {

  const handleInputChange = (e) => {
    // Replace any non-numeric and non-decimal characters with an empty string
    const sanitizedValue = e.target.value.replace(/[^0-9.]/g, '');

    // Ensure there's only one decimal point
    const hasDecimalPoint = sanitizedValue.includes('.');
    if (!hasDecimalPoint || (hasDecimalPoint && e.data !== '.')) {
      setSelectedOption(sanitizedValue);
    }
  };

  return (
    <input
      type="text"
      value={selectedOption}
      placeholder="Enter Shoe Size"
      onChange={handleInputChange}
    />
  );
};

export default ShoeSizeMenu;
