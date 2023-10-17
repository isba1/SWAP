import React, {useState} from "react";
import styles from "./style.module.css";
import Login from "./components/LoginForm";
import Register from "./components/RegisterForm";
import Picture from "./components/Accountpic";
import Title from "./components/Title";

function Account() {
  const[currentForm, setCurrentForm] = useState("login");
  const toggleForm = (formName) =>{
    setCurrentForm(formName);
  }
  return (<div>
      <Title/>
      <div className={styles.row}>
        <div className={styles.columnleft}>
          <Picture/>
        </div>
        <div className={styles.columnright}>
          {
            currentForm === "login" ? <Login onFormSwitch={toggleForm}/> : <Register onFormSwitch={toggleForm}/>
          }
        </div>
      </div>
    </div>
  );
}

export default Account;
