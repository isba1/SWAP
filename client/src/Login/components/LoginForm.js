import axios from 'axios';
import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import styles from '../style.module.css';
import jwtDecode from 'jwt-decode';


const Login = (props) =>{
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const [userId, setUserId] = useState(null);
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
                // const userObjectID = convertUuidToObjectId(decodedToken.userID);
                setUserId(decodedToken.userID);
                console.log(decodedToken);
                navigate('/home');
            }
        } catch (error) {
            console.error(error);
        }
    };

    // const convertUuidToObjectId = (uuid) => {
    //     // Assuming uuid is a string in the format 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'
    //     // Remove hyphens and convert to hexadecimal
    //     const hexString = uuid.replace(/-/g, '');
    //
    //     // Convert hexadecimal string to ObjectId
    //     const objectId = new ObjectId(hexString, 16);
    //
    //     // Return the ObjectId as a string
    //     return objectId.toString();
    // };

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

        {/* You can use userId wherever you need it in your component */}
        {/*{userId && (*/}
        {/*    <div>*/}
        {/*        <p>User ID: {userId}</p>*/}
        {/*        /!* Example: Pass userId as a prop to a child component *!/*/}
        {/*        <ChildComponent userId={userId} />*/}
        {/*    </div>*/}
        {/*)}*/}

    </div>)
}

export default Login;