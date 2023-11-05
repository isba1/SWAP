import React from 'react';
import axios from 'axios';

function NameList({array, toggle, post, myid}) {

  const handleButtonClick = async (name, item) =>{
    console.log(`Button Clicked for ${name}`);
    console.log(item.postID);
    //temp is supposted to be the postID of the post you chose to offer
    const temp = "6543fc5d0d9510298230d106";
    try{
      //await axios.post(`http://localhost:8080/offer/makeOffer?sellerPostID=${post.postID}&sellerUserID=${post.userID}&buyerPostID=${temp}&buyerUserID=${myid}`);
      toggle();
    }catch (error){
      console.error(error);
    }
  }

  if (array === null){
    return <div>Loading...</div>;
  }

  if (array.length === 0) {
    return <div>You have no Posts! Start Posting to offer a trade</div>;
  }

  return (
    <div>
      {array.map((item, index) => (
        <button key={index} onClick={() => handleButtonClick(item.name, item)}>
          <img src={item.base64Images[0]} alt={item.name}/>
        </button>
      ))}
    </div>
  );
}

export default NameList;
