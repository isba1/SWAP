import React, { useState } from "react";
import styles from '../style.module.css';
import axios from "axios";
import { useNavigate } from 'react-router-dom'

const Register = (props) =>{
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [name, setName] = useState('');
    const [number, setNumber] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [zip, setZip] = useState('');
    const [emailStatus, setEmailStatus] = useState('');


    const navigate = useNavigate();

    const handleSubmit = async (event) =>{
        event.preventDefault();
        const userInfoRequestBody = {
            fullName: name,
            email: email,
            password: pass,
            phone: number,
            city: city,
            state: state,
            zipCode: zip
        }
        // write axios request to sign up end point here
        try {
            const response = await axios.post(`http://localhost:8080/login/saveAccountInfo`, userInfoRequestBody);
            setEmailStatus(response.data)
            if (emailStatus) {
                navigate('/newUserInterests');
            } else {
                setEmailStatus('Email already exists. Please choose a different email.');
                setEmail('');
                setPass('');
                setName('');
                setNumber('');
                setCity('');
                setState('');
                setZip('');
            }

        } catch (error) {
            console.error(error);
        }
    }


    return (<div className= {styles.formcontainer}>
        <h1 className={styles.header}>Want To Start Swapping? Register!</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
            <input value={name} name="name" id="name" placeholder= "Full Name" onChange={(e) =>setName(e.target.value)}></input>
            <input value={email} type="Email" placeholder = "Email Address" id= "Email" name="Email" onChange={(e) =>setEmail(e.target.value)}></input>
            <input value={pass} type="Password" placeholder = "Password" id= "Password" name="Password" onChange={(e) =>setPass(e.target.value)}></input>
            <input value={number} type="Number" placeholder="Phone Number" id="Number" name="Number" onChange={(e) =>setNumber(e.target.value)}></input>
            <input value={city} type="City" placeholder="City" id="City" name="City" onChange={(e) =>setCity(e.target.value)}></input>
            <input value={state} type="State" placeholder="State" id="State" name= "State" onChange={(e) =>setState(e.target.value)}></input>
            <input value={zip} type="Zip" placeholder="Zip-Code" id="Zip" name= "Zip" onChange={(e) =>setZip(e.target.value)}></input>
            <button type="submit">Register</button>
        </form>
        {emailStatus && <p className={styles['error-message']}>{emailStatus}</p>}

        <button className={styles.linkbtn} onClick={() => props.onFormSwitch('login')}>Already have an account? Login here!</button>
    </div>)
    }

export default Register;