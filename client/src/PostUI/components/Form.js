import React, { useState } from "react";
import "./Post.css"
import DropdownMenu from "./DropdownMenu";
import BrandMenu from "./BrandMenu";
import StyleMenu from "./StyleMenu";
import SizeMenu from "./SizeMenu";

import axios from "axios";

function convertByteArrayToBase64String(byteArray) {
    const binaryString = new TextDecoder().decode(byteArray);

    // Convert binary string to base64
    const base64String = btoa(unescape(encodeURIComponent(binaryString)));

    return base64String;
}

function convertByteArraysToBase64StringList(byteArrays) {
    const base64StringList = [];

    byteArrays.forEach((byteArray) => {
        const base64String = convertByteArrayToBase64String(byteArray);
        base64StringList.push(base64String);
    });

    return base64StringList;
}


const Form = ({toggle, selectedFiles ,setSelectedFiles}) =>{
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [brand, setBrand] = useState('');
    const [style, setStyle] = useState('');
    const [size, setSize] = useState('');

    const handleUpload = async (event) => {
        event.preventDefault();
        if (selectedFiles.length === 0) {
            console.error('Please select at least one file.');
            return;
        }

        const promises = Array.from(selectedFiles).map((file) => {
            return new Promise((resolve, reject) => {
                const reader = new FileReader();

                reader.onload = (event) => {
                    const byteArray = new Uint8Array(event.target.result);
                    resolve(byteArray);
                };

                reader.readAsArrayBuffer(file);
            });
        });
    
        try {
            const byteArrays = await Promise.all(promises);

            const base64StringList = convertByteArraysToBase64StringList(byteArrays);

            const postRequestBody = {
                    base64Images: base64StringList,
                    name: productName,
                    postDescription: description,
                    postCategory: category,
                    postBrand: brand,
                    postStyle: style,
                    postSize: size
                }
            console.log(postRequestBody);
            const testUserID = "6535f4d4c8497a19ddd89a7e";
            // Send the array of Uint8Arrays to the backend
            await axios.post(`http://localhost:8080/post/newPost?UserID=${testUserID}`, postRequestBody);

            //console.log('Images uploaded successfully.');
            setProductName('');
            setDescription('');
            setCategory('');
            setBrand('');
            setSize('');
            setStyle('');
            toggle();
            setSelectedFiles([]);
        } catch (error) {
            console.error('Error uploading images:', error);
        }
    };


    return(<div className="formcontainer">
        <h1 className="header">New Post</h1>
        <form className="form" onSubmit={handleUpload}>
            <input value={productName} name="name" id="name" placeholder="Product Name" onChange={(e) =>setProductName(e.target.value)}></input>
            <input value={description} name="description" id="description" placeholder="Product Description" onChange={(e) =>setDescription(e.target.value)}></input>
            <DropdownMenu selectedOption={category} setSelectedOption={setCategory}/>
            <BrandMenu selectedOption={brand} setSelectedOption={setBrand}/>
            <StyleMenu selectedOption={style} setSelectedOption={setStyle}/>
            <SizeMenu selectedOption={size} setSelectedOption={setSize}/>
            <button type="submit">POST</button>
        </form>
    </div>)
}

export default Form;