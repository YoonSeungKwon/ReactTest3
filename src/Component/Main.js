import React, {useState, useEffect} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";

const Main = () =>{

    const [login, setLogin] = useState("Login")
    const [register, setRegister] = useState("회원가입")

    const headers = {
        Authorization:"Bearer "+localStorage.getItem("ACCESS_TOKEN"),
        'X-RefreshToken':"Bearer "+localStorage.getItem("REFRESH_TOKEN")
    }

    const navigate = new useNavigate();

    const handleClick = (e) =>{
        if(login === "Logout"){
            localStorage.clear()
            navigate("/api/v1/login")
        }
    }

    useEffect(()=>{
        axios.get("/api/v1/auth/check", {headers
        }).then(function(response){
            console.log(response.data)
            if(response.data === true) {
                console.log(response)
                setLogin("Logout")
                setRegister("")
                if(response.headers.get("Authorization") != null)
                    localStorage.setItem("ACCESS_TOKEN", response.headers.get("authorization"))
            }
            else {
                setLogin("Login")
                setRegister("회원가입")
            }
        })
    }, [])

    return(
        <>
            <h4>Main Page</h4>
            <Link to="/api/v1/login" onClick={handleClick}>{login}</Link>
            <br/>
            <Link to="/api/v1/register">{register}</Link>
            <br/>
            <Link to="/api/v1/test">사용자 정보</Link>
            <br/>
            <Link to="/api/v1/boards">게시판</Link>
        </>
    )
}
export default Main;