import styled, { createGlobalStyle } from "styled-components";
import { Logo } from "../../style/logo";


const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 40px;
    margin: 0 auto;
    width: 300px;
    padding: 100px 50px;
    border: 1px solid black;
    border-radius: 10px;
    input {
        width: 100%;
        padding: 10px;
        border: none;
        background-color: lightgray;
        border-radius: 5px;
        font-family: "Open Sans", sans-serif;
    }
    button {
        font-family: "Open Sans", sans-serif;
        width: 100%;
        padding: 10px;
        border: none;
        background-color: var(--main-color);
        color: white;
        font-weight: bolder;
        font-size: medium;
        border-radius: 5px;
        cursor: pointer;
    }
`;

const ControlContainer = styled.div`
    display: flex;
    gap: 5px;
    p {
        cursor: pointer;
    }
`;

/* 
    - Control Container 매뉴들 routeData 에서 가져오기 
    - button input 사이즈 조정 필요 
    - global style 로 적용시 fontfamily 적용 안됨 확인 필요
    - Logo 사이즈 조정 필요 props 전달하여 사이즈 조정 필요    
 */

export const LoginContainer = () => {
    return (
        <FormContainer>
            <Logo header="black" span="#F26F23" />
            <input type="text" placeholder="username" />
            <input type="password" placeholder="password" />
            <ControlContainer>
                <p>비밀번호 찾기</p>
                <p>|</p>
                <p>아이디 찾기</p>
                <p>|</p>
                <p>회원가입</p>
            </ControlContainer>
            <button type="submit">로그인</button>
        </FormContainer>
    );
};