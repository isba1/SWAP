import React, { useState, useEffect } from "react";
import axios from "axios";
import "./profile.css";

const MyFollowers = ({place, myID}) =>{
    const [modal, setModal] = useState(false);
    const [data, setData] = useState(null);

    useEffect(() => {
        const fetchData = async() => {
            const response = await axios.get(`http://localhost:8080/profile/followers?userID=${myID}`);
            const user = await response.data;
            setData(user);
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
        <button className='showfollowbutton' onClick={toggleModal}>{place}</button>

        {modal &&(
        <div className="showfollowmodal">
          <div className="showfollowoverlay" onClick={toggleModal}></div>
          <div className="showfollowmodal-content">
            <h2>Followers:</h2>
            <button className="showfollowclose-modal" onClick={toggleModal}>X</button>
            </div> 
        </div>
      )}
    </div>
    )
}

export default MyFollowers;
