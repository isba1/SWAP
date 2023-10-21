import styles from '../style.module.css';
import React, {useState} from "react";

const Login = (props) =>{
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');

    const handleSubmit = (event) =>{
        event.preventDefault();
        // write axios request to log in end point here
        console.log("Email:", email);
        console.log("Password", pass);
        setEmail("");
        setPass("");
        
    }
    return (<div className= {styles.formcontainer}>
        <h1 className={styles.header}>Welcome Back!</h1>
        <h1 className={styles.header}>Sign in.</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <input value={email} type="Email" placeholder = "Email Address" id= "Email" name="Email" onChange={(e) =>setEmail(e.target.value)}></input>
            <input value={pass} type="Password" placeholder = "Password" id= "Password" name="Password" onChange={(e) =>setPass(e.target.value)}></input>
            <button type="submit">Log In</button>
        </form>
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('register')}>Don't have an account? Register here!</button>
    </div>)
}

export default Login;