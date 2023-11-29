import React from "react";
import "./profile.css";

const FollowerFollowing = ({click, userName}) =>{
    return(<div key={userName}>
        <button className="followexbutton" onClick={click}>
            {userName}
        </button>
    </div>);
};

export default FollowerFollowing;