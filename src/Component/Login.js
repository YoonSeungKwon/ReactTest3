import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const Login = () =>{

    const navigate = new useNavigate()

    const [state, setState] = useState({
        email:"",
        password:""
    })

    const handleChange = (e) =>{
        const {id , value} = e.target
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }
    const handleClick = () =>{
        axios.post("/api/v1/members/login", state
        ).then(function(response){
            console.log(response)
            localStorage.setItem("ACCESS_TOKEN", response.headers.get("authorization"))
            localStorage.setItem("REFRESH_TOKEN", response.headers.get("x-refreshtoken"))
            alert(response.data.message)
            navigate("/")
        }).catch(function(error){
            console.log(error)
            alert(error.response.data)
            setState({
                email:"",
                password:""
            })
        })
    }

    return (

        <>
            <h4>Login Page</h4>
            <br/>
            <input
                type="text"
                name="email"
                id="email"
                value={state.email}
                onChange={handleChange}
            />:    Email<br/>
            <input
                type="password"
                name="password"
                id="password"
                value={state.password}
                onChange={handleChange}
            />:    Password<br/>
            <input
                type="submit"
                value="Login"
                onClick={handleClick}
            />
        </>
    )
}
export default Login;