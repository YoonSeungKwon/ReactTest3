import React, {useState, useEffect} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
const Register = () =>{

    const navigate = new useNavigate();

    const [check, setCheck] = useState(false);

    const [message, setMessage] = useState("")

    const [state, setState] = useState({
        email:"",
        password:"",
        name:""
    });

    const handleChange = (e) =>{
        const {id , value} = e.target
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }

    /*개발중*/
    const handleCheckEmail = () =>{
        axios.get("/api/v1/member/${state.email}/exists"
        ).then(function(response){
            console.log(response)
            setCheck(true)
            alert(response.data)
        }).catch(function(error){
            console.log(error)
            alert(error.response.data)
        })
    }
    /**/

    const handleSubmit = () =>{
        axios.post("/api/v1/members/join", state
        ).then(function(response){
                console.log(response)
                alert("회원가입 성공")
                navigate("/api/v1/login")
            }
        ).catch(function (error){
                console.log(error.response)
                alert(error.response.data)
                navigate("/api/v1/register")
            }
        )

    }

    return (

        <>
            <h4>Register Page</h4>
            <input
                type="text"
                name="email"
                id="email"
                value={state.email}
                onChange={handleChange}
            />:    Email<input type="submit" onClick={handleCheckEmail} value="중복체크"></input>
            <br/>
            <input
                type="password"
                name="password"
                id="password"
                value={state.password}
                onChange={handleChange}
            />:    Password
            <br/>
            <input
                type="text"
                name="name"
                id="name"
                value={state.name}
                onChange={handleChange}
            />:    Name
            <br/>
            <input
                type="submit"
                value="Register"
                onClick={handleSubmit}
            />
        </>
    )
}
export default Register;