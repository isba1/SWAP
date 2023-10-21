import React, { useState, useEffect } from 'react';
import "./Image.css"

const Slideshow = ({ imageFiles }) => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % imageFiles.length);
    }, 5000);

    return () => {
      clearInterval(interval);
    };
  }, [imageFiles]);

  return (
    <div className="slideshow-container">
      <img
        src={URL.createObjectURL(imageFiles[currentImageIndex])}
        alt="Slideshow"
        className="slideshow-image"
      />
    </div>
  );
};

export default Slideshow;
