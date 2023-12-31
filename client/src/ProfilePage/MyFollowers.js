import React, { useState, useEffect, Suspense } from "react";
import axios from "axios";
import "./profile.css";
import { useNavigate } from 'react-router-dom';

const NameButton = React.lazy(() => import('./FollowerFollowing'));

const MyFollowers = ({place, myID}) =>{
    const [modal, setModal] = useState(false);
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    
    const navigate = useNavigate();
    const handleProfileChange = async (userName) => {
        navigate(`/userprofile/${userName}`);
    }

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`http://localhost:8080/profile/followers?userID=${myID}`);
                const user = response.data;
                setData(user);
            } catch (error) {
                console.error("Error fetching data:", error);
            } finally {
                setLoading(false);
            }
        };

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
            {loading ? (
                            <p>Loading...</p>
                        ) : (
                            <div>
                                {data && data.length > 0 ? (
                                <Suspense fallback={<p>Loading followers...</p>}>
                                    {data.map(item => (
                                    <NameButton
                                        key={item.userID}
                                        userName={item.userName}
                                        click={() => handleProfileChange(item.userName)}
                                    />
                                    ))}
                                </Suspense>
                                ) : (
                                    <div>
                                        <p>You have no followers! Get connected with other users!</p>
                                    </div>
                                )}
                            </div>
                        )}
            </div> 
        </div>
      )}
    </div>
    )
}

export default MyFollowers;