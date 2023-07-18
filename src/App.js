import Header from './Component/Header'
import Main from './Component/Main'
import Login from './Component/Login'
import Register from './Component/Register'
import Test from './Component/Test'
import {BrowserRouter, Route, Routes} from "react-router-dom";
const App = () => {
  return (
    <div>
        <Header />
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main />}></Route>
                <Route path="/api/v1/login" element={<Login />}></Route>
                <Route path="/api/v1/register" element={<Register />}></Route>
                <Route path="/api/v1/test" element={<Test />}></Route>
            </Routes>
        </BrowserRouter>
    </div>
  )
}

export default App;
