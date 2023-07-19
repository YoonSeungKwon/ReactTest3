import React, {useState, useEffect} from "react";
import "./Board.css";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";


const Board = props =>{

    const navigate = new useNavigate();

    const [list, setList] = useState([]);

    const headers = {
        Authorization:"Bearer "+localStorage.getItem("ACCESS_TOKEN"),
        'X-RefreshToken':"Bearer "+localStorage.getItem("REFRESH_TOKEN")
    };


    useEffect(()=>{
        axios.get("/api/v1/posts/", {headers
        }).then(function (response){
            console.log(response)
            setList(response.data)
        }).catch(function (error){
            console.log(error)
            alert("회원만 이용가능 합니다.")
            navigate("/api/v1/login")
        })
    },[])


    return(
        <>
            <h3 className="title">게시판</h3>
            <Link to="/api/v1/boards/link">글쓰기</Link>
            <table>
                <thead>
                <tr>
                    <td className="table_index table_head table_font">순번</td>
                    <td className="table_title table_head table_font">제목</td>
                    <td className="table_writer table_head table_font">작성자</td>
                    <td className="table_regdate table_head table_font">게시일</td>
                    <td className="table_hit table_head table_font">조회수</td>
                </tr>
                </thead>
                <tbody>
                {list.map((item)=>(
                    <tr>
                        <td className="table_index table_body table_font">{item.idx}</td>
                        <td className="table_title table_body table_font"><Link to={`/api/v1/boards/details/${item.idx}`}>{item.title}</Link></td>
                        <td className="table_writer table_body table_font">{item.writer}</td>
                        <td className="table_regdate table_body table_font">{item.regdate}</td>
                        <td className="table_hit table_body table_font">{item.hit}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </>
    )
}
export default Board;