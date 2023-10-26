import React from 'react';
import styles from './interest.module.css';
import { useNavigate } from 'react-router-dom';
//import axios from 'axios';

const InterestSelection = (props) => {
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            navigate('/home');
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className={styles.iformcontainer}>
            <h1 className={styles.iheader}>Tell Us About Your Fashion Interests</h1>
            <form className={styles.iform} onSubmit={handleSubmit}>

                <label className={styles.iname}>Clothing style:</label>
                <div>
                    {/* List out the clothing styles, make sure to add or remove as needed */}
                    {["Casual", "Formal", "Vintage", "Streetwear", "Bohemian", "Preppy", "Androgynous", "Goth", "Hipster"].map(style => (
                        <button className={styles.ibutton} key={style}>{style}</button>
                    ))}
                </div>
                <button className={styles.ilinkbtn} type='submit'>You're done! Login!</button>
            </form>
        </div>
    );
}

export default InterestSelection;

