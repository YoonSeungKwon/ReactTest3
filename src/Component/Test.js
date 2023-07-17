import React,{useState, useEffect} from "react";
import axios from "axios";

const Test = () =>{

    const [data, setData] = useState({
        email:"",
        name:"",
        role:"",
        regdate:""
    });
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
    })},[])

    return(
        <>
            <p>회원정보</p>
            <p>{data.email}</p>:Email
            <p>{data.name}</p>:Name
            <p>{data.role}</p>:Role
            <p>{data.regdate}</p>:Regdate
        </>
    )
}

export default Test;