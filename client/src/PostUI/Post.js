import React, {useState} from "react";
import "./components/Post.css"
import FileUpload from "./components/FileUpload";
import Slideshow from "./components/ImageSlideshow";
import Form from "./components/Form"

function Post() {
  const [modal, setModal] = useState(false);
  const [modal2, setModal2] = useState(false);
  const [files, setFiles] = useState([]);

  const toggleModal = () => {
    setModal(!modal);
  }

  const toggleModal2 = () =>{
    setModal2(!modal2);
  }

  if (modal || modal2){
    document.body.classList.add('active-modal')
  }else{
    document.body.classList.remove('active-modal')
  }
  return (
    <div>
      <button onClick={toggleModal} className="btn-modal">Post</button>

      {modal &&(
        <div className="modal">
          <div className="overlay" onClick={toggleModal}></div>
          <div className="modal-content">
            <h2>Upload Photos of Your Product</h2>
            <div style={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
              <FileUpload toggle={toggleModal2} close={toggleModal} selectedFiles={files} setSelectedFiles={setFiles}/>
            </div>
            <button className="close-modal" onClick={toggleModal}>X</button>
            </div> 
        </div>
      )}

      {modal2 &&(
        <div className="modal">
        <div className="overlay" onClick={toggleModal2}></div>
        <div className="modal-content">
          <div className="row">
            <div className="columnleft">
              <Slideshow imageFiles={files}/>
            </div>
            <div className="columnright">
              <Form/>
            </div>
          </div>
          <button className="close-modal" onClick={toggleModal2}>X</button>
          </div> 
      </div>        
      )}
    </div>
  );
}

export default Post;
