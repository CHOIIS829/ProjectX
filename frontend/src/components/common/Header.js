import React from 'react';
import styled from "styled-components";
import { Logo } from "../../style/logo";
import { RouteData } from "../../assets/routeData";
import { useNavigate } from "react-router-dom";

const HeaderContainer = styled.header`
    background-color: yellow;
    height: 5vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    font-weight: bold;
    color: white;
    background-color: var(--main-color);
`;

const RouteContainer = styled.div`
    display: flex;
    gap: 10px;
    cursor: pointer;
    h3:nth-child(odd):hover {
        color: black;
    }
`;

export const Header = () => {
    const navigate = useNavigate();

    const filteredRoutes = Object.values(RouteData).filter(route => {
        return route.path === '/login' || route.path === '/signup';
    });

    const routeList = filteredRoutes.map((route, index) => {
        return (
            <React.Fragment key={route.path}>
                <h3 onClick={() => navigate(route.path)}>{route.titleEnglish}</h3>
                {index < filteredRoutes.length - 1 && <span>|</span>}
            </React.Fragment>
        );
    });

    return (
        <HeaderContainer>
            <Logo header="white" span="black" />
            <RouteContainer>
                {routeList}
            </RouteContainer>
        </HeaderContainer>
    );
};