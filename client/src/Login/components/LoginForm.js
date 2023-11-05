import axios from 'axios';
import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import styles from '../style.module.css';


const Login = (props) =>{
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const navigate = useNavigate();


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.get(`http://localhost:8080/login/reoccurringUser?email=${email}&password=${pass}`);
            setLoginStatus(response.data.loginSuccess);
            if (response.data.loginSuccess) {
                const token = response.data.tokenString;
                const payload = token.split('.')[1];
                const decodedToken = JSON.parse(atob(payload));
                sessionStorage.setItem('userID', decodedToken.userID);
                navigate('/home');
            }
        } catch (error) {
            console.error(error);
        }
    };


    return (<div className= {styles.formcontainer}>
        <h1 className={styles.header}>Welcome Back!</h1>
        <h1 className={styles.header}>Sign in.</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <input value={email} type="Email" placeholder = "Email Address" id= "Email" name="Email" onChange={(e) =>setEmail(e.target.value)}></input>
            <input value={pass} type="Password" placeholder = "Password" id= "Password" name="Password" onChange={(e) =>setPass(e.target.value)}></input>
            <button type="submit">Log In</button>
        </form>
        {loginStatus === false && (
            <p className={styles['error-message']}>
                Invalid credentials
            </p>
        )}
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('register')}>Don't have an account? Register here!</button>


    </div>)
}

export default Login;