import { Logo } from "../../style/logo";
import styled from "styled-components";
import { useState } from "react";


const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 27%;
    gap: 20px;
    padding: 50px 30px;
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
            background: darkgrey;
        }
    }
`;

const FormContainers = styled.div`
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: row;
    gap: 10px;
    input {
        width: 80%;
        padding: 10px;
        border: none;
        background-color: lightgrey;
        border-radius: 5px;
        &::placeholder {
            font-weight: bold;
        }
    }
    button {
        width: 20%;
        padding: 10px;
        border: none;
        background: var(--main-color);
        color: white;
        font-weight: bold;
        font-size: medium;
        border-radius: 5px;
        cursor: pointer;
        &:hover {
            background: darkgrey;
        }
    }
`;

const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const gitUrlRegex = /^(https:\/\/|git@)(github\.com|bitbucket\.org|gitlab\.com)[:\/][\w.-]+\/[\w.-]+(\.git)?$/;

export const SignUpContainer = () => {
    const [id, setId] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [passwordCheck, setPasswordCheck] = useState("");
    const [telCheck, setTelCheck] = useState("");
    const [gitUrl, setGitUrl] = useState("");
    const [idValidation, setIdValidation] = useState(false);
    const [emailValidation, setEmailValidation] = useState(false);
    const [passwordValidation, setPasswordValidation] = useState(false);
    const [passwordCheckValidation, setPasswordCheckValidation] = useState(false);
    const [telCheckValidation, setTelCheckValidation] = useState(false);
    const [gitUrlValidation, setGitUrlValidation] = useState(false);
    const [passwordBlurred, setPasswordBlurred] = useState(false);
    const [passwordCheckBlurred, setPasswordCheckBlurred] = useState(false);
    const [gitUrlBlurred, setGitUrlBlurred] = useState(false);

    const handlePasswordChange = (e) => {
        const value = e.target.value;
        setPassword(value);
        setPasswordValidation(passwordRegex.test(value));
    };

    const handlePasswordCheckChange = (e) => {
        const value = e.target.value;
        setPasswordCheck(value);
        setPasswordCheckValidation(value === password);
    };

    const handleGitUrlChange = (e) => {
        const value = e.target.value;
        setGitUrl(value);
        setGitUrlValidation(gitUrlRegex.test(value));
    };

    const handleBlur = (e, setBlurred) => {
        setBlurred(true);
    };

    const handleSubmit = (e) => {
        e.preventDefault(); 
    };

    return (
        <FormContainer onSubmit={handleSubmit}>
            <Logo header="black" span="#F26F23" tag="h1" />
            <br />
            <br />
            <FormContainers>
                <input type="text" placeholder="아이디를 입력하세요." />
                <button>중복 확인</button>
            </FormContainers>
            <FormContainers>
                <input type="text" placeholder="이메일 입력하세요." />
                <button>중복 확인</button>
            </FormContainers>
            <input
                type="password"
                placeholder="비밀번호를 입력하세요."
                value={password}
                onChange={handlePasswordChange}
                onBlur={(e) => handleBlur(e, setPasswordBlurred)}
            />
            {passwordBlurred && (
                passwordValidation ? 
                <p style={{ color: 'green' }}>올바른 비밀번호 입니다.</p> :
                <p style={{ color: 'red' }}>올바르지 않은 비밀번호 입니다.</p>
            )}
            <input
                type="password"
                placeholder="비밀번호를 다시 입력하세요."
                value={passwordCheck}
                onChange={handlePasswordCheckChange}
                onBlur={(e) => handleBlur(e, setPasswordCheckBlurred)}
            />
            {passwordCheckBlurred && (
                passwordCheckValidation ? 
                <p style={{ color: 'green' }}>비밀번호가 일치 합니다.</p> :
                <p style={{ color: 'red' }}>비밀번호가 일치 하지 않습니다.</p>
            )}
            <input
                type="text"
                placeholder="Git 주소를 입력하세요."
                value={gitUrl}
                onChange={handleGitUrlChange}
                onBlur={(e) => handleBlur(e, setGitUrlBlurred)}
            />
            {gitUrlBlurred && (
                gitUrlValidation ? 
                <p style={{ color: 'green' }}>올바른 Git 주소 입니다.</p> :
                <p style={{ color: 'red' }}>올바르지 않은 Git 주소 입니다.</p>
            )}
            <button type="submit">Sign Up</button>
        </FormContainer>
    );
};