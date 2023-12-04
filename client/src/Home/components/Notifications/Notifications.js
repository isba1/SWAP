import './Notifications.css';
import React, {useCallback, useMemo} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export const Notifications = ({ notifications, deleteFirstNotification }) => {
  const userID = sessionStorage.getItem("userName");

  const navigate = useNavigate();

  const handleProfileChange = async (userName) => {
      navigate(`/userprofile/${userName}`);
  } 

  const textToDisplay = useMemo(() => {
    if (!notifications.length) return ''
    console.log('notification', notifications[0])
    return (<div>
      <p className='notifbody'>Your offer on {notifications[0].postName} was {notifications[0].accepted ? "accepted" : "declined"} by </p>
      <button className="notifprofile" onClick={() => handleProfileChange(notifications[0].userName)}>{notifications[0].userName}</button>
    </div>)
  }, [notifications])

  const handleClick = useCallback(async () => {
    if (!notifications.length) return
    const response = await axios.delete(`http://localhost:8080/profile/deleteNotification?userID=${userID}&notificationID=${notifications[0].id}`)
    console.log(`Deleting notification, received response: ${response.data}`)
    if (response.data) {
      deleteFirstNotification()
    }
  }, [notifications])

  const deleteNotificationText = useMemo(() => {
    if (!notifications.length) return ''
    if (notifications.length > 1) return 'Delete and view next notification'
    return 'Delete notification'
  }, [notifications])

  if (!notifications.length) return null
  return <div className={'notification'}>
    <div className={'notification-inner'}>{textToDisplay}</div>
    <div className={'notification-click-info'} onClick={handleClick}>{deleteNotificationText}</div>
  </div>
}
