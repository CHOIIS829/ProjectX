import styled from "styled-components";
import { FindIdContainer } from "../components/findId/findIdContainer";

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
`;

export const FindId = ()=> {
    return(
        <Container>
            <FindIdContainer/>
        </Container>
    );
}