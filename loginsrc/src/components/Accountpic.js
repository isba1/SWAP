import React from 'react';
import styles from '../style.module.css';
import image from './Login.jpg'

const Picture = () =>{
    const style={
        position: "relative",
        margin: "auto",
    }
    return(<div className={styles.container}>
        <img src={image} alt="Swap Clothes"
        height={510.33} width={340.25}
        style ={style}/>
    </div>);
}

export default Picture;