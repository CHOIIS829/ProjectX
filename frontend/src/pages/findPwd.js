import styled from "styled-components";
import { FindPwdContainer } from "../components/findPwd/findPwdContainer";  

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
`;

export const FindPwd = ()=> {
    return(
        <Container>
            <FindPwdContainer/>
        </Container>
    );  
};