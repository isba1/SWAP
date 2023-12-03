import React from 'react';
import axios from 'axios';
import "./Offer.css";

function NameList({array, toggle, post, myid}) {

  const handleButtonClick = async (name, item) =>{
    try{
      await axios.post(`http://localhost:8080/offer/makeOffer?sellerPostID=${post.postID}&sellerUserID=${post.userName}&buyerPostID=${item.postID}&buyerUserID=${myid}`);
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
        <button className='namebutton' key={index} onClick={() => handleButtonClick(item.name, item)}>
          <img className='offerdisplaypost' src={item.gcsUrls[0]} alt={item.name}/>
        </button>
      ))}
    </div>
  );
}

export default NameList;
