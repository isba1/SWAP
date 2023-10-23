import React, { useState } from "react";
import "./Post.css"
import DropdownMenu from "./DropdownMenu";

const Form = ({toggle, setSelectedFiles}) =>{
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');

    const handleSubmit = (event) =>{
        event.preventDefault();
        console.log(productName, description, category);
        setProductName('');
        setDescription('');
        setCategory('');
        setSelectedFiles([]);
        toggle();
        //AXIOS HERE
    }

    return(<div className="formcontainer">
        <h1 className="header">New Post</h1>
        <form className="form" onSubmit={handleSubmit}>
            <input value={productName} name="name" id="name" placeholder="Product Name" onChange={(e) =>setProductName(e.target.value)}></input>
            <input value={description} name="description" id="description" placeholder="Product Description" onChange={(e) =>setDescription(e.target.value)}></input>
            <DropdownMenu selectedOption={category} setSelectedOption={setCategory}/>
            <button type="submit">POST</button>
        </form>        
    </div>)
}

export default Form;