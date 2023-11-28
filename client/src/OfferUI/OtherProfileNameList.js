import React from 'react';
import axios from 'axios';

function OtherProfileNameList({array, toggle, post, myid, sellerid}) {

  const handleButtonClick = async (name, item) =>{
    //console.log(`Button Clicked for ${name}`);
    //not sure if I have to convert ID for post of not
    //console.log(item.postID);
    //temp is supposted to be the postID of the post you chose to offer
    try{
      await axios.post(`http://localhost:8080/offer/makeOffer?sellerPostID=${post.postID}&sellerUserID=${sellerid}&buyerPostID=${item.postID}&buyerUserID=${myid}`);
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
          <img src={item.base64Images[0]} alt={item.name}/>
        </button>
      ))}
    </div>
  );
}

export default OtherProfileNameList;