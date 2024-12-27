import styled from "styled-components";
import React from "react";
import { Logo } from "../../style/logo";
import { useNavigate } from "react-router-dom";
import { filterRoutes } from "../../assets/routeData"; 

const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 45px;
    padding: 100px 60px;
    border: 1px solid lightgrey;
    border-radius: 10px;
    input {
        width: 100%;
        padding: 10px;
        border: none;
        background-color: lightgrey;
        border-radius: 5px;
        &::placeholder {
            font-weight: bold;
        }
    }
    button {
        width: 100%;
        padding: 10px;
        border: none;
        background: var(--main-color);
        color: white;
        font-weight: bold;
        font-size: medium;
        border-radius: 5px;
        cursor: pointer;
        &:hover {
            color: black;
        }
    }
`;

const ControlContainer = styled.div`
    display: flex;
    gap: 10px;
    p {
        cursor: pointer;
        font-size: medium;
    }
    p:nth-child(odd):hover {
        color: var(--main-color);
    }
`;


export const LoginContainer = () => {
    
    const navigate = useNavigate();

    const makeRoutes = () => {
        const filteredRoutes = filterRoutes('/find-email', '/signup', '/find-password');
        return filteredRoutes.map((route, index) => {
            return (
                <React.Fragment key={route.path}>
                    <p onClick={() => navigate(route.path)}>{route.titleKorean}</p>
                    {index < filteredRoutes.length - 1 && <span>|</span>}
                </React.Fragment>
            );
        });
    };

    return (
        <FormContainer>
            <Logo header="black" span="#F26F23" tag="h1"/>
            <input type="text" placeholder="아이디를 입력하세요." />
            <input type="password" placeholder="비밀번호를 입력하세요." />
            <ControlContainer>
                {makeRoutes()}
            </ControlContainer>
            <button type="submit">로그인</button>
        </FormContainer>
    );
};