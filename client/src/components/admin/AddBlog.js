import React, { useRef, useState } from 'react'

import '../../theme/admin/AddBlog.css';
import ICONS from '../../constants/admin/icons';
import InputFile from '../../components/admin/InputFile';
import { imageStorage } from '../../config/FirebaseConfig';
import { ref, uploadBytes } from 'firebase/storage';



function AddBlog() {

  const [title, setTitle] = useState();
  const [description,setDescription] = useState();
  const [blogTagName,setBlogTagName] = useState();
  const [imageData,setImageData] = useState([]);
  const [fileImage,setFileImage] = useState([]);



  const formatDoc = (cmd, value) => {
    if (value) {
      document.execCommand(cmd, false, value);
    } else {
      document.execCommand(cmd);
    }
  }
  const addLink = () => {
    const url = prompt('Insert url');
    formatDoc('createLink', url);
  }


  const handleMouseenter = () => {
    const content = document.getElementById('content');
    const a = content.querySelectorAll('a');
    a.forEach(item => {
      item.addEventListener('click', function () {
        content.setAttribute('contenteditable', false);
        item.target = '_blank';
      })
      item.addEventListener('mouseleave', function () {
        content.setAttribute('contenteditable', true);
      })
    })
  }

  const hanleAddBlog = () => {
    const content = document.getElementById('content');
    
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let imgFile =[];
    fileImage.forEach((file,_index) => {
        if(file){
          const now = Date.now();
          const url = `uploads/${"_image_blog" + now}`;
          const imageRef = ref(imageStorage,url);
          uploadBytes(imageRef,file);
          imgFile.push(url);
        }
    });

   
    
    const blogObject = {
      "title":title,
      "description": description,
      "contentDetail": content.innerHTML,
      "tagName": blogTagName,
      "image": imgFile[0],
      "active": true
    }

    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(blogObject),
      redirect: "follow"
    };
    
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/blog/create`, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        if(result === "true") {
          window.location.href = window.location.href;
        }
      })
      .catch((error) => console.error(error));
 
  }

  let active = false;
  const handleOnclickShowCode = () => {
    const content = document.getElementById('content');
    active = !active;
    if (active) {
      content.textContent = content.innerHTML;
      content.setAttribute('contenteditable', false);
    } else {
      content.innerHTML = content.textContent;
      content.setAttribute('contenteditable', true);
    }
  }


  const hanldeDescription = (event) => {
    const value = event.target.value;
    setDescription(value)
  }
  const handleBlogTagName = (event) => {
    const value = event.target.value;
    setBlogTagName(value.toUpperCase())
  }



  return (
    <div className='add-blog-admin-container'>
      <div className='bt-add-blog' onClick={() => hanleAddBlog()}>
        <span>ADD BLOG</span>
      </div>
      <div className='toolbar'>
        <div className='head'>
          <select
            onChange={(event) => formatDoc('formatBlock', event.target.value)}
          >
            <option value="" selected="" hidden="" disabled="">Format</option>
            <option value="h1">Heading 1</option>
            <option value="h2">Heading 2</option>
            <option value="h3">Heading 3</option>
            <option value="h4">Heading 4</option>
            <option value="h5">Heading 5</option>
            <option value="h6">Heading 6</option>
            <option value="p">Paragraph</option>
          </select>
          <select
            onChange={(event) => formatDoc('fontSize', event.target.value)}
          >
            <option value="" selected="" hidden="" disabled="">Font size</option>
            <option value="1">Extra small</option>
            <option value="2">Small</option>
            <option value="3">Regular</option>
            <option value="4">Medium</option>
            <option value="5">Large</option>
            <option value="6">Extra Large</option>
            <option value="7">Big</option>
          </select>
          <div class="color">
            <span>Color</span>
            <input type="color" onInput={(event) => formatDoc('foreColor', event.target.value)} />
          </div>
          <div class="color">
            <span>Hight light</span>
            <input type="color" onInput={(event) => formatDoc('hiliteColor', event.target.value)} />
          </div>
        </div>

        <div className='btn-toolbar'>
          <button onClick={(event) => formatDoc('undo', event)}><img src={ICONS.icon_undo} alt='' /></button>
          <button onClick={(event) => formatDoc('redo', event)}><img src={ICONS.icon_redo} alt='' /></button>
          <button onClick={(event) => formatDoc('bold', event)}><img src={ICONS.icon_bold} alt='' /></button>
          <button onClick={(event) => formatDoc('underline', event)}><img src={ICONS.icon_underline} alt='' /></button>
          <button onClick={(event) => formatDoc('italic', event)}><img src={ICONS.icon_italic} alt='' /></button>
          <button onClick={(event) => formatDoc('strikeThrough', event)}><img src={ICONS.icon_strike_through} alt='' /></button>
          <button
            onClick={(event) => formatDoc('justifyLeft', event)} ><img src={ICONS.icon_justify_left} alt='' /></button>
          <button
            onClick={(event) => formatDoc('justifyCenter', event)}
          ><img src={ICONS.icon_justify_center} alt='' /></button>
          <button
            onClick={(event) => formatDoc('justifyRight', event)}
          ><img src={ICONS.icon_justify_right} alt='' /></button>
          <button
            onClick={(event) => formatDoc('justifyFull', event)}
          ><img src={ICONS.icon_justify_full} alt='' /></button>
          <button
            onClick={(event) => formatDoc('insertOrderedList', event)}
          ><img src={ICONS.icon_list_ol} alt='' /></button>
          <button
            onClick={(event) => formatDoc('insertUnorderedList', event)}
          ><img src={ICONS.icon_list_ul} alt='' /></button>

          <button
            onClick={() => addLink()}><img src={ICONS.icon_link} alt='' /></button>
          <button
            onClick={(event) => formatDoc('unlink', event)}
          ><img src={ICONS.icon_unlink} alt='' /></button>
          <button
            onClick={() => handleOnclickShowCode()}
            data-active="false"
          ><img src={ICONS.icon_code} alt='' /></button>
        </div>
      </div>
      <div className='content-blog' >
        <div className='head-blog-content'>
          <div>
            <h5>BLOG TAG NAME</h5>
            <input 
              onChange={(event) => handleBlogTagName(event)}
            type='text' />
          </div>
          <div>
            <h3>Title</h3>

            <input
              onChange={(event) => {
                setTitle(event.target.value)
              }}
              type='text' />


          </div>
          <div>
            <h5>Description</h5>
            <textarea 
              onChange={(event) => hanldeDescription(event)}
            />
          </div>


          <div>
            <h5>
              Image thumnail
            </h5>
            <div>
              <InputFile
                _width={"160px"}
                setImageData={setImageData}
                setFileImage={setFileImage}
                index={0}
                length={1}
                imageData={imageData}
                fileImage={fileImage}
              />
              <div className='img-thumnail-container'>
                  <img  src={imageData} alt='' />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="content" className='main-content' contentEditable="true"
        onClick={() => handleMouseenter()}

      >
        <h1>{title}</h1>
      </div>
    </div>
  )
}

export default AddBlog