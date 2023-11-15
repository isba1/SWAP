import React, { useState, useEffect } from "react";
import axios from "axios";
import "./profile.css";

const MyFollowering = ({place}) =>{
    const [modal, setModal] = useState(false);
    const [data, setData] = useState(null);

    useEffect(() => {
        async function fetchData() {
            //this fetches data about your posts when you press the Offer button
        }
        if (modal) {
            fetchData();
        }
    }, [modal]);

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
        <button className='showfollowbutton' onClick={toggleModal}>{place}</button>

        {modal &&(
        <div className="showfollowmodal">
          <div className="showfollowoverlay" onClick={toggleModal}></div>
          <div className="showfollowmodal-content">
            <h2>Following:</h2>
            <button className="showfollowclose-modal" onClick={toggleModal}>X</button>
            </div> 
        </div>
      )}
    </div>
    )
}

export default MyFollowering;
