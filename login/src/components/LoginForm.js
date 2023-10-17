import styles from '../style.module.css';
import React, {useState} from "react";

const Login = (props) =>{
    const [user, setUser] = useState('');
    const [pass, setPass] = useState('');

    const handleSubmit = (event) =>{
        event.preventDefault();
        console.log(user);
    }

    return (<div className= {styles.formcontainer}>
        <h1 className={styles.header}>Sign in.</h1>
        <h1 className={styles.header}>Welcome Back!</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <label htmlFor= "Username">Username</label>
            <input value={user} type="Username" placeholder = "Username" id= "Username" name="Username"></input>
            <label htmlFor= "Password">Password</label>
            <input value={pass} type="Password" placeholder = "*******" id= "Password" name="Password"></input>
            <button type="submit">Log In</button>
        </form>
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('register')}>Don't have an account? Register here!</button>
    </div>)
}

export default Login;