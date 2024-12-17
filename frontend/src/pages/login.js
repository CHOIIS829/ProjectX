import { LoginContainer } from "../components/login/loginContainer";
import styled from "styled-components";

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
`;

/*
    - Container CSS 공통 빼기 
*/

export const Login = () => {
    return (
        <Container>
            <LoginContainer />
        </Container>
    );
};