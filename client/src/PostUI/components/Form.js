import React, { useState } from "react";
import "./Post.css"
import DropdownMenu from "./DropdownMenu";
import BrandMenu from "./BrandMenu";

const Form = ({toggle, selectedFiles ,setSelectedFiles}) =>{
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [brand, setBrand] = useState('');

    const handleSubmit = (event) =>{
        event.preventDefault();
        console.log(productName, description, category, brand);
        //AXIOS HERE, PICTURE FILES ARE IN "selectedFiles" array
        setProductName('');
        setDescription('');
        setCategory('');
        setBrand([]);
        toggle();
        setSelectedFiles([]);
    }

    return(<div className="formcontainer">
        <h1 className="header">New Post</h1>
        <form className="form" onSubmit={handleSubmit}>
            <input value={productName} name="name" id="name" placeholder="Product Name" onChange={(e) =>setProductName(e.target.value)}></input>
            <input value={description} name="description" id="description" placeholder="Product Description" onChange={(e) =>setDescription(e.target.value)}></input>
            <DropdownMenu selectedOption={category} setSelectedOption={setCategory}/>
            <BrandMenu selectedOption={brand} setSelectedOption={setBrand}/>
            <button type="submit">POST</button>
        </form>        
    </div>)
}

export default Form;