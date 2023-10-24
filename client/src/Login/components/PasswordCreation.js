// PasswordCreation.js
import React, { useState } from 'react';
import styles from '../style.module.css';

const PasswordCreation = (props) => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const handleSubmit = (event) =>{
        props.onFormSwitch('interests');
    }
    return (
    <div className={styles.formcontainer}>
        <h1 className={styles.header}>Welcome to Our Platform!</h1>
        <h1 className={styles.header}>Password Creation</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <label>Password</label>
            <input 
                type="password" 
                value={password} 
                onChange={(e) => setPassword(e.target.value)} 
                placeholder="Must be at least 8 characters"
                id="PasswordCreation" 
                name="PasswordCreation" 
            />
            <label>Confirm Password</label>
            <input 
                type="password" 
                value={confirmPassword} 
                onChange={(e) => setConfirmPassword(e.target.value)} 
                placeholder="Both passwords must match"
                id="ConfirmPassword" 
                name="ConfirmPassword" 
            />
            <button type="submit">Continue</button>
        </form>
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('login')}>Already have an account? Login here!</button>
    </div>
    );
}

export default PasswordCreation;

