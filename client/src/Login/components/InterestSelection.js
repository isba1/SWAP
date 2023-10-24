import React from 'react';
import styles from '../style.module.css';

const InterestSelection = (props) => {
    return (
        <div className={styles.formcontainer}>
            <h1 className={styles.header}>Tell Us About Your Fashion Interests</h1>
            <form className={styles.form}>

                <label className={styles.name}>Clothing style:</label>
                <div>
                    {/* List out the clothing styles, make sure to add or remove as needed */}
                    {["Casual", "Formal", "Vintage", "Streetwear", "Bohemian", "Preppy", "Androgynous", "Goth", "Hipster"].map(style => (
                        <button className={styles.button} key={style}>{style}</button>
                    ))}
                </div>

                {/* Assuming you want to add sections for Patterns, Sizes, Preferred brands similarly in the future */}

            </form>
            <button type="submit" className={styles.linkbtn} onClick={() => props.onFormSwitch('passwordCreation')}>Last Step!</button>
        </div>
    );
}

export default InterestSelection;

