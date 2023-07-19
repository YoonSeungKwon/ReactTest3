import React,{useState, useEffect} from "react";
import axios from "axios";
import "./Test.css";
import {useNavigate} from "react-router-dom";
const Test = () =>{

    const [data, setData] = useState({
        email:"",
        name:"",
        roleValue:"",
        regdate:""
    });

    const navigate = new useNavigate()

    const headers = {
        Authorization:"Bearer "+localStorage.getItem("ACCESS_TOKEN"),
        'X-RefreshToken':"Bearer "+localStorage.getItem("REFRESH_TOKEN")
    }

    useEffect(()=>{
        axios.get("/test/v1/user", {headers}).then(function (response){
        setData(response.data)
        console.log(response)
    }).catch(function (error){
        console.log(error)
        alert("회원만 이용가능 합니다.")
        navigate("/api/v1/login")
    })},[])

    return(
        <>
            <p>회원정보</p>
            <span className="label">Email</span><span>{data.email}</span><br/>
            <span className="label">Name:</span><span>{data.name}</span><br/>
            <span className="label">Role:</span><span>{data.roleValue}</span><br/>
            <span className="label">Regdate:</span><span>{data.regdate}</span>
        </>
    )
}

export default Test;