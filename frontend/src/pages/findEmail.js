import styled from "styled-components";
import { FindEmailContainer } from "../components/findEmail/findEmailContainer";

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
`;

export const FindEmail = ()=> {
    return(
        <Container>
            <FindEmailContainer/>
        </Container>
    );
}