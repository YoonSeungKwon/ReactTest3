import React from "react";
import {Link} from "react-router-dom";

const Main = () =>{
    return(
        <>
            <h4>Main Page</h4>
            <Link to="/api/v1/login">Login</Link>
            <br/>
            <Link to="/api/v1/register">Register</Link>
        </>
    )
}
export default Main;