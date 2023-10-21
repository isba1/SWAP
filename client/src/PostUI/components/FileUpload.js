import React from 'react';
import "./Post.css";

function FileUpload({toggle, close, selectedFiles, setSelectedFiles}) {

  const handleFileChange = (event) => {
    const files = event.target.files;
    //file types that won't be allowed
    const imageTypes = ['image/jpeg', 'image/png', 'image/jpg', 'image/bmp'];

    //check to see if the select image is in the allowed file types
    const selectedImages = Array.from(files).filter((file) =>
      imageTypes.includes(file.type)
    );

    //add files to array
    setSelectedFiles([...selectedFiles, ...selectedImages]);
  };

  const handleRemoveFile = (file) => {
    const updatedFiles = selectedFiles.filter((f) => f !== file);
    setSelectedFiles(updatedFiles);
  };

  const handleUpload = () => {
    if (selectedFiles.length === 0) {
      alert('Please select one or more image files to upload.');
      return;
    }

    // You can perform further processing here, such as uploading the image files to a server.
    console.log('Selected Image Files:', selectedFiles);
    // Clear the selected files
    close();
    toggle();
    //setSelectedFiles([]);
  };

  return (
    <div>
      <input
        type="file"
        accept="image/*"
        multiple
        onChange={handleFileChange}
      />
      <button onClick={handleUpload} className="selectbtn">Upload</button>
      <div>
        <h3>Selected Image Files:</h3>
        <h3>
          {selectedFiles.map((file, index) => (
            <li key={index}>
              {file.name}
              <button onClick={() => handleRemoveFile(file)} className="removebtn">X</button>
            </li>
          ))}
        </h3>
      </div>
    </div>
  );
}

export default FileUpload;
