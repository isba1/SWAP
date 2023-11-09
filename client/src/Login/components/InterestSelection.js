import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../style.module.css';

const brandsList = ["Nike", "Adidas", "Puma", "Under Armor", "Ralph Lauren", "Calvin Klein", "Levi's", "Tommy Hilfiger", "Patagonia", "Lacoste", "Other"];
const styleList = ["Casual", "Formal", "Vintage", "Streetware", "Goth", "Other"];
const shirtSizeList = ["XS", "S", "M", "L", "XL", "XXL"];
const jacketSizeList = ["XS", "S", "M", "L", "XL", "XXL"];
const pantSizeList = ["XS", "S", "M", "L", "XL", "XXL"];


const InterestSelection = (props) => {
    const [brands, setBrands] = useState([]);
    const [clothingStyle, setClothingStyle] = useState([]);
    const [shirtSize, setShirtSize] = useState('');
    const [shoeSize, setShoeSize] = useState('');
    const [pantSize, setPantSize] = useState('');
    const [jacketSize, setJacketSize] = useState('');

    const navigate = useNavigate();

    const handleBrandChange = (brand) => {
        if (brands.includes(brand)) {
            setBrands(brands.filter((b) => b !== brand));
        } else {
            setBrands([...brands, brand]);
        }
    };

    const handleStyleChange = (style) => {
        if (clothingStyle.includes(style)) {
            setClothingStyle(clothingStyle.filter((s) => s !== style));
        } else {
            setClothingStyle([...clothingStyle, style]);
        }
    };

    const handleSubmit = async () => {
        const userPreferenceRequestBody = {
            brands,
            clothingStyle,
            shirtSize,
            shoeSize,
            pantSize,
            jacketSize
        }

        const userID = sessionStorage.getItem("userID");

        try {
            await axios.post(`http://localhost:8080/newUserInterests/saveUserInterests?userID=${userID}`, userPreferenceRequestBody);
            navigate('/home');
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className={styles.formcontainer}>
            <h1 className={styles.header}>Tell Us About Your Fashion Interests</h1>

            <label className={styles.name}>Brands:</label>
            <div className={styles.checklistContainer}>
                {brandsList.map((brand) => (
                    <button
                        key={brand}
                        className={`${styles.checkboxButton} ${brands.includes(brand) ? styles.selected : ''}`}
                        onClick={() => handleBrandChange(brand)}
                    >
                        {brand}
                    </button>
                ))}
            </div>

            <label className={styles.name}>Clothing Styles:</label>
            <div className={styles.checklistContainer}>
                {styleList.map((style) => (
                    <button
                        key={style}
                        className={`${styles.checkboxButton} ${clothingStyle.includes(style) ? styles.selected : ''}`}
                        onClick={() => handleStyleChange(style)}
                    >
                        {style}
                    </button>
                ))}
            </div>

            <label className={styles.name}>Shirt Size:</label>
            <div className={styles.checklistContainer}>
                {shirtSizeList.map((size) => (
                    <button
                        key={size}
                        className={`${styles.checkboxButton} ${shirtSize === size ? styles.selected : ''}`}
                        onClick={() => setShirtSize(size)}
                    >
                        {size}
                    </button>
                ))}
            </div>

            <label className={styles.name}>Jacket Size:</label>
            <div className={styles.checklistContainer}>
                {jacketSizeList.map((size) => (
                    <button
                        key={size}
                        className={`${styles.checkboxButton} ${jacketSize === size ? styles.selected : ''}`}
                        onClick={() => setJacketSize(size)}
                    >
                        {size}
                    </button>
                ))}
            </div>

            <label className={styles.name}>Pant Size:</label>
            <div className={styles.checklistContainer}>
                {pantSizeList.map((size) => (
                    <button
                        key={size}
                        className={`${styles.checkboxButton} ${pantSize === size ? styles.selected : ''}`}
                        onClick={() => setPantSize(size)}
                    >
                        {size}
                    </button>
                ))}
            </div>

            <label className={styles.name}>Shoe Size:</label>
            <input
                type="text"
                placeholder="Enter Shoe Size"
                value={shoeSize}
                onChange={(e) => setShoeSize(e.target.value)}
                className={styles.inputBox}
            />


            <button type="button" onClick={handleSubmit} className={styles.linkbtn}>Submit Interests</button>

        </div>
    );
}

export default InterestSelection;
