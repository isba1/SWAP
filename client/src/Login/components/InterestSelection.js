import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../style.module.css';


const InterestSelection = (props) => {
    const [selectedStyle, setSelectedStyle] = useState(''); // Initialize the selectedStyle state
    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        const userPreferenceRequestBody = {
            clothingStyle: selectedStyle // Only the selected clothing style
        }

        try {
            await axios.post(`http://localhost:8080/login/saveAccountInfo`, userPreferenceRequestBody);
            // navigate('/post'); --> If you have a navigate function
        } catch (error) {
            console.error(error);
        }

        props.onFormSwitch('passwordCreation');
    }

    return (
        <div className={styles.formcontainer}>
            <h1 className={styles.header}>Tell Us About Your Fashion Interests</h1>
            <form className={styles.form}>

                <label className={styles.name}>Clothing style:</label>
                <div>
                    {/* List out the clothing styles, make sure to add or remove as needed */}
                    {["Casual", "Formal", "Vintage", "Streetwear", "Bohemian", "Preppy", "Androgynous", "Goth", "Hipster"].map(style => (
                        <button className={styles.button} key={style}>{style} onClick={() => setSelectedStyle(style)}</button>
                    ))}
                </div>

            </form>
            <button type="submit" className={styles.linkbtn} onClick={() => props.onFormSwitch('passwordCreation')}>Last Step!</button>
        </div>
    );
}

export default InterestSelection;

