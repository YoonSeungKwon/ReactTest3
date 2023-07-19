import React, {useState, useEffect} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const BoardWrite = () =>{

    const navigate = new useNavigate();

    const styleTitle= {
        marginTop:50,
        width:300
    }
    const styleContent= {
        marginTop:20,
        width:300,
        height:200
    }

    const [state, setState] = useState({
        title:"",
        content:""
    })

    const headers = {
        Authorization:"Bearer "+localStorage.getItem("ACCESS_TOKEN"),
        'X-RefreshToken':"Bearer "+localStorage.getItem("REFRESH_TOKEN")
    }

    const handleChange = (e) =>{
        const {id , value} = e.target
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }

    const handleClick = () =>{
        axios.post("/api/v1/posts/link", state , {headers
        }).then(function(response){
            console.log(response)
            navigate("/api/v1/boards")
        }).catch(function(error){
            console.log(error)
        })
    }

    return(
        <>
            <div className="post_title">
                <label>제목
                    <input style={styleTitle}
                           id="title"
                           type="text"
                           name="title"
                           onChange={handleChange}
                           value={state.title}/>
                </label>
            </div>
            <br/>
            <div className="post_content">
                <label>내용
                    <textarea style={styleContent}
                              id="content"
                              name="content"
                              onChange={handleChange}
                              value={state.content}></textarea>
                </label>
            </div>
            <input type="submit" onClick={handleClick} value="제출"/>
        </>
    )
}
export default BoardWrite;