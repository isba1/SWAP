import styles from '../style.module.css';
import axios from 'axios';
import React, {useState} from "react";
import { useNavigate } from 'react-router-dom';


const Login = (props) =>{
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [loginStatus, setLoginStatus] = useState(null);
    const navigate = useNavigate();

    // write axios request to log in end point here
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.get(`http://localhost:8080/login/reoccurringUser?email=${email}&password=${pass}`);
            setLoginStatus(response.data);
            if (response.data) {
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
        {loginStatus !== null && (
            <p className={`${loginStatus ? '' : styles['error-message']}`}>
                {loginStatus ? 'Login successful' : 'Invalid credentials'}
            </p>
        )}
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('register')}>Don't have an account? Register here!</button>
    </div>)
}

export default Login;