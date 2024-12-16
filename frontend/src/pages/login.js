import { LoginContainer } from "../components/login/loginContainer";
import styled from "styled-components";

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80vh;
`;

export const Login = () => {
    return(
        <Container>
            <LoginContainer/>
        </Container>
    );
};