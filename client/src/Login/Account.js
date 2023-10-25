import React, { useState } from "react";
import Picture from "./components/Accountpic";
import InterestSelection from "./components/InterestSelection";
import Login from "./components/LoginForm";
import PasswordCreation from "./components/PasswordCreation";
import Register from "./components/RegisterForm";
import Title from "./components/Title";
import styles from "./style.module.css";

function Account() {
  const [currentScreen, setCurrentScreen] = useState("login");
  
  const switchScreen = (screenName) => {
    setCurrentScreen(screenName);
  }

  let renderedComponent;
  switch (currentScreen) {
    case 'login':
      renderedComponent = <Login onFormSwitch={switchScreen} />;
      break;
    case 'register':
      renderedComponent = <Register onFormSwitch={switchScreen} />;
      break;
    case 'passwordCreation':
      renderedComponent = <PasswordCreation onFormSwitch={switchScreen} />;
      break;
    case 'interests':
      renderedComponent = <InterestSelection onFormSwitch={switchScreen} />;
      break;
    default:
      renderedComponent = <Login onFormSwitch={switchScreen} />;
  }

  return (
    <div>
      <Title />
      <div className={styles.row}>
        <div className={styles.columnleft}>
          <Picture />
        </div>
        <div className={styles.columnright}>
          {renderedComponent}
        </div>
      </div>
    </div>
  );
}

export default Account;

