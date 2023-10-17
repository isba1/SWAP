import styles from '../style.module.css';
import React, {useState} from "react";

const Register = (props) =>{
    const [user, setUser] = useState('');
    const [pass, setPass] = useState('');
    const [name, setName] = useState('');

    const handleSubmit = (event) =>{
        event.preventDefault();
        console.log(user);
    }

    return (<div className= {styles.formcontainer}>
        <h1 className={styles.header}>Want To Start Swapping? Register!</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <label htmlFor="name">Full Name</label>
            <input value={name} name="name" id="name" placeholder= "Full Name"></input>
            <label htmlFor= "Username">Username</label>
            <input value={user} type="Username" placeholder = "Username" id= "Username" name="Username"></input>
            <label htmlFor= "Password">Password</label>
            <input value={pass} type="Password" placeholder = "*******" id= "Password" name="Password"></input>
            <button type="submit">Register</button>
        </form>
        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('login')}>Already have an account? Login here!</button>
    </div>)
}

export default Register;