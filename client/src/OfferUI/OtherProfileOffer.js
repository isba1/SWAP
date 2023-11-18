import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Offer.css";
import OtherProfileNameList from "./OtherProfileNameList";

const OtherProfileOffer = ({SellerPost, myID, sellerid}) => {
    const [modal, setModal] = useState(false);
    const [data, setData] = useState(null);

    useEffect(() => {
        async function fetchData() {
            //this fetches data about your posts when you press the Offer button
            if (data === null){
                const response = await axios.get(`http://localhost:8080/offer/itemsToOffer?userID=${myID}`);
                const user = await response.data;
                setData(user);
            }
        }
        if (modal) {
            fetchData();
        }
    }, [modal, myID]);

    const toggleModal = () => {
        if (modal){
            setData(null);
        }
        setModal(!modal);
    }

    if (modal){
        document.body.classList.add('active-modal')
      }else{
        document.body.classList.remove('active-modal')
    }

    return(<div>
        <button onClick={toggleModal}>Make Offer</button>

        {modal &&(
        <div className="offermodal">
          <div className="offeroverlay" onClick={toggleModal}></div>
          <div className="offermodal-content">
            <h2>Choose Your Post to Offer</h2>
            <OtherProfileNameList array={data} toggle={toggleModal} post={SellerPost} myid={myID} sellerid={sellerid}/>
            <button className="offerclose-modal" onClick={toggleModal}>X</button>
            </div> 
        </div>
      )}
    </div>
    )
}

export default OtherProfileOffer;