import { BrowserRouter,Routes, Route  } from "react-router-dom";
import { Main } from "../pages/main"
import { Login } from "../pages/login";
import { Header } from "../components/common/Header";

export const Router = () =>{
    return(
        <BrowserRouter> 
            <Routes>
                <Route path="/" element={
                    <>
                        <Header/>
                        <Main/>
                    </>
                }/>
                <Route path="/login" element={
                    <Login/>
                }/>
            </Routes>
        </BrowserRouter>
    );
};