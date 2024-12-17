import React from 'react';
import styled from "styled-components";
import { Logo } from "../../style/logo";
import { filterRoutes } from "../../assets/routeData";
import { useNavigate } from "react-router-dom";

const HeaderContainer = styled.header`
    background-color: yellow;
    height: 7vh;
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
        color: lightgray;
    }
`;

export const Header = () => {
    const navigate = useNavigate();

    const makeRoutes = () => {
        const filteredRoutes = filterRoutes('/login', '/signup');
        return filteredRoutes.map((route, index) => {
            return (
                <React.Fragment key={route.path}>
                    <h3 onClick={() => navigate(route.path)}>{route.titleEnglish}</h3>
                    {index < filteredRoutes.length - 1 && <span>|</span>}
                </React.Fragment>
            );
        });
    };

    return (
        <HeaderContainer>
            <Logo header="white" span="black" tag="h2"/>
            <RouteContainer>
                {makeRoutes()}
            </RouteContainer>
        </HeaderContainer>
    );
};