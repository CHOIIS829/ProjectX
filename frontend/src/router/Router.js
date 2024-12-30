import { BrowserRouter,Routes, Route  } from "react-router-dom";
import { Main } from "../pages/main"
import { Login } from "../pages/login";
import { SignUp } from "../pages/signUp";
import { FindId } from "../pages/findId";
import { Header } from "../components/common/Header";
import { FindPwd } from "../pages/findPwd";

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
                <Route path="/signUp" element={
                    <SignUp/>
                }/>
                <Route path="/find-id" element={
                    <FindId/>
                } />
                <Route path="/find-password" element={
                    <FindPwd/>
                } />
            </Routes>
        </BrowserRouter>
    );
};