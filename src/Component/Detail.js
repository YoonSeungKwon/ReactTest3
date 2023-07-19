import React, {useState, useEffect} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

const Detail = (props) =>{

    const navigate = new useNavigate();

    const style = {
        fontWeight:"bold",
        marginRight:50,
    }

    let {idx} = useParams();

    const [state, setState] = useState({
        idx:"",
        title:"",
        content:"",
        writer:"",
        regdate:"",
        hit:""
    })

    const headers = {
        Authorization:"Bearer "+localStorage.getItem("ACCESS_TOKEN"),
        'X-RefreshToken':"Bearer "+localStorage.getItem("REFRESH_TOKEN")
    }

    useEffect(()=>{
        console.log(idx)
        axios.get(`/api/v1/posts/details/${idx}`, {headers
        }).then(function(response){
            console.log(response)
            setState(response.data)
        }).catch(function (error){
            console.log(error)
        })
    },[])

    return(
        <>
            <p><span style={style}>글번호:</span>{state.idx}</p>
            <p><span style={style}>제목 : </span>{state.title}</p>
            <p><span style={style}>내용 : </span>{state.content}</p>
            <p><span style={style}>작성자: </span>{state.writer}</p>
            <p><span style={style}>작성일: </span>{state.regdate}</p>
            <p><span style={style}>조회수: </span>{state.hit}</p>
        </>
    )

}
export default Detail;