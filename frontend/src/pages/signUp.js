import styled from "styled-components";
import {SignUpContainer} from "../components/signup/signUpContainer";

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
`;

export const SignUp = () => {
    return(
        <Container>
            <SignUpContainer/>
        </Container>
    );
};