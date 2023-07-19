import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from './Component/Header'
import Main from './Component/Main'
import Login from './Component/Login'
import Register from './Component/Register'
import Test from './Component/Test'
import Board from "./Component/Board";
import BoardWrite from "./Component/BoardWrite";
import Detail from "./Component/Detail";
import {useEffect, useState} from "react";
const App = () => {

  return (
    <div>
        <Header />
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/api/v1/login" element={<Login />}/>
                <Route path="/api/v1/register" element={<Register/>}/>
                <Route path="/api/v1/test" element={<Test/>}/>
                <Route path="/api/v1/boards" element={<Board/>}/>
                <Route path="/api/v1/boards/link" element={<BoardWrite/>}/>
                <Route path="/api/v1/boards/details/:idx" element={<Detail/>}/>
            </Routes>
        </BrowserRouter>
    </div>
  )
}

export default App;
