import React, { useState } from "react";
import "./Post.css";
import DropdownMenu from "./DropdownMenu";
import BrandMenu from "./BrandMenu";
import StyleMenu from "./StyleMenu";
import SizeMenu from "./SizeMenu";

import axios from "axios";

const Form = ({ toggle, selectedFiles, setSelectedFiles }) => {
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [brand, setBrand] = useState('');
    const [style, setStyle] = useState('');
    const [size, setSize] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleUpload = async (event) => {
        event.preventDefault();

        if (selectedFiles.length === 0) {
            console.error('Please select at least one file.');
            return;
        }

        if (productName === '' || description === '' || category === '' || brand === '' || style === '' || size === '') {
            alert('All fields must be filled');
            return;
        }

        const file = selectedFiles[0];

        // Check if the file size exceeds the limit (1MB)
        if (file.size > 1048576) {
            setErrorMessage('File size exceeds the limit (1MB)');
            return;
        }

        setErrorMessage('');

        const formData = new FormData();
        formData.append(`image`, file);

        try {
            // Send the array of MultipartFiles to the backend
            const response = await axios.post(
                "http://localhost:8080/post/newPostImage",
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                }
            );

            const postID = response.data;

            const postRequestBody = {
                name: productName,
                postDescription: description,
                postCategory: category,
                postBrand: brand,
                postStyle: style,
                postSize: size,
            };

            console.log(postRequestBody);

            const userID = sessionStorage.getItem("userName");
            await axios.post(`http://localhost:8080/post/newPostInfo?userID=${userID}&postID=${postID}`, postRequestBody);

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

    return (
        <div className="formcontainer">
            <h1 className="header">New Post</h1>
            <form className="form" onSubmit={handleUpload}>
                <input value={productName} name="name" id="name" placeholder="Product Name" onChange={(e) => setProductName(e.target.value)}></input>
                <input value={description} name="description" id="description" placeholder="Product Description" onChange={(e) => setDescription(e.target.value)}></input>
                <DropdownMenu selectedOption={category} setSelectedOption={setCategory} />
                <BrandMenu selectedOption={brand} setSelectedOption={setBrand} />
                <StyleMenu selectedOption={style} setSelectedOption={setStyle} />
                <SizeMenu selectedOption={size} setSelectedOption={setSize} />
                <button type="submit">POST</button>
                {errorMessage && <p style={{ color: 'red', marginBottom: '10px' }}>{errorMessage}</p>}
            </form>
        </div>
    );
};

export default Form;
