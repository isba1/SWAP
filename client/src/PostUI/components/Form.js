import React, { useState } from "react";
import "./Post.css"
import DropdownMenu from "./DropdownMenu";
import BrandMenu from "./BrandMenu";
import axios from "axios";

const Form = ({toggle, selectedFiles ,setSelectedFiles}) =>{
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [brand, setBrand] = useState('');

    // const convertImagesToByteArrays = (imageFiles) => {
    //     const promises = [];
    //
    //     for (const file of imageFiles) {
    //       promises.push(new Promise((resolve, reject) => {
    //         const reader = new FileReader();
    //
    //         reader.onload = (event) => {
    //           const dataUrl = event.target.result;
    //           const byteString = atob(dataUrl.split(',')[1]);
    //           const byteArray = new Uint8Array(byteString.length);
    //
    //           for (let i = 0; i < byteString.length; i++) {
    //             byteArray[i] = byteString.charCodeAt(i);
    //           }
    //
    //           resolve(byteArray);
    //         };
    //
    //         reader.readAsDataURL(file);
    //       }));
    //     }
    //
    //     return Promise.all(promises);
    //   };

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

            const postRequestBody = {
                    image: byteArrays,
                    postDescription: description,
                    postCategory: category,
                    postBrand: brand
                }
            console.log(postRequestBody);
            const testUserID = "6535f4d4c8497a19ddd89a7e";
            // Send the array of Uint8Arrays to the backend
            await axios.post(`http://localhost:8080/post/newPost?UserID=${testUserID}`, postRequestBody);

            //console.log('Images uploaded successfully.');
        } catch (error) {
            console.error('Error uploading images:', error);
        }
    };

    // const handleSubmit = async (event) =>{
    //     event.preventDefault();
    //     try {
    //         const imageByteArrays = await convertImagesToByteArrays(selectedFiles);
    //
    //         const postRequestBody = {
    //             imageBinary: imageByteArrays,
    //             postDescription: description,
    //             postCategory: category,
    //             postBrand: brand
    //         }
    //         console.log(postRequestBody);
    //
    //         //using a test user id until Justin finished session generation
    //         const testUserID = "6535f4d4c8497a19ddd89a7e";
    //
    //         await axios.post(`http://localhost:8080/post/newPost?UserID=${testUserID}`, postRequestBody);
    //
    //     } catch (error) {
    //         console.error(error);
    //     }
    //
    // }

    return(<div className="formcontainer">
        <h1 className="header">New Post</h1>
        <form className="form" onSubmit={handleUpload}>
            <input value={productName} name="name" id="name" placeholder="Product Name" onChange={(e) =>setProductName(e.target.value)}></input>
            <input value={description} name="description" id="description" placeholder="Product Description" onChange={(e) =>setDescription(e.target.value)}></input>
            <DropdownMenu selectedOption={category} setSelectedOption={setCategory}/>
            <BrandMenu selectedOption={brand} setSelectedOption={setBrand}/>
            <button type="submit">POST</button>
        </form>
    </div>)
}

export default Form;