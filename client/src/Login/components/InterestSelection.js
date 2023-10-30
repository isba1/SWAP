
import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../style.module.css';

const InterestSelection = (props) => {
    const [brands, setBrands] = useState([]);
    const [clothingCategory, setClothingCategory] = useState([]);
    const [shirtSize, setShirtSize] = useState('');
    const [shoeSize, setShoeSize] = useState('');
    const [pantSize, setPantSize] = useState('');
    const [jacketSize, setJacketSize] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const userPreferenceRequestBody = {
            brands,
            clothingCategory,
            shirtSize,
            shoeSize,
            pantSize,
            jacketSize
        }

        try {
            await axios.post(`http://localhost:8080/login/saveAccountInfo`, userPreferenceRequestBody);
            navigate('/home');
        } catch (error) {
            console.error(error);
        }

        props.onFormSwitch('passwordCreation');
    }

    return (
        <div className={styles.formcontainer}>
            <h1 className={styles.header}>Tell Us About Your Fashion Interests</h1>
            <form className={styles.form} onSubmit={handleSubmit}>

                <label className={styles.name}>Brands:</label>
                <select onChange={e => setBrands(prev => [...prev, e.target.value])}>
                    <option value="BrandA">Brand A</option>
                    <option value="BrandB">Brand B</option>
                    <option value="BrandC">Brand C</option>
                    {/* ... */}
                </select>
                
                <label className={styles.name}>Clothing style:</label>
                <select onChange={e => setClothingCategory(e.target.value)}>
                    <option value="Casual">Casual</option>
                    <option value="Formal">Formal</option>
                    <option value="Vintage">Vintage</option>
                    <option value="Streetwear">Streetwear</option>
                    <option value="Bohemian">Bohemian</option>
                    <option value="Preppy">Preppy</option>
                    <option value="Androgynous">Androgynous</option>
                    <option value="Goth">Goth</option>
                    <option value="Hipster">Hipster</option>
                </select>

                <label className={styles.name}>Shirt Size:</label>
                <select onChange={e => setShirtSize(e.target.value)}>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                    <option value="XXL">XXL</option>
                </select>

                <label className={styles.name}>Shoe Size:</label>
                <select onChange={e => setShoeSize(e.target.value)}>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>

                <label className={styles.name}>Pant Size:</label>
                <select onChange={e => setPantSize(e.target.value)}>
                    <option value="28">28</option>
                    <option value="30">30</option>
                    <option value="32">32</option>
                    <option value="34">34</option>
                    <option value="36">36</option>
                    {/* ... */}
                </select>

                <label className={styles.name}>Jacket Size:</label>
                <select onChange={e => setJacketSize(e.target.value)}>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                    <option value="XXL">XXL</option>
                </select>

                <button type="submit" className={styles.linkbtn}>Last Step!</button>

            </form>
        </div>
    );
}

export default InterestSelection;


