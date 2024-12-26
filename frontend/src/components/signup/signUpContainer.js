import { Logo } from "../../style/logo";
import styled from "styled-components";
import { useState } from "react";
import { checkId, join } from "../../api/authApi";
import { emailCheck,checkEmail } from "../../api/mailApi"; 

const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 35%;
    gap: 35px;
    padding: 50px 50px;
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
const gitProfileUrlRegex = /^https:\/\/github\.com\/[\w-]+$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

export const SignUpContainer = () => {

    // 아이디
    const [id, setId] = useState("");
    const [idValidation, setIdValidation] = useState(false);
    const [idValidationMessage, setIdValidationMessage] = useState("");

    // 이메일
    const [email, setEmail] = useState(""); 
    const [emailValidation, setEmailValidation] = useState(false);
    const [emailValidationMessage, setEmailValidationMessage] = useState("");

    // 이메일 인증코드
    const [emailCode, setEmailCode] = useState("");
    const [emailCodeValidation, setEmailCodeValidation] = useState(false);
    const [emailCodeValidationMessage, setEmailCodeValidationMessage] = useState("");

    //비밀번호 
    const [password, setPassword] = useState("");
    const [passwordValidation, setPasswordValidation] = useState(false);
    const [passwordBlurred, setPasswordBlurred] = useState(false);

    //비밀번호 확인
    const [passwordCheck, setPasswordCheck] = useState("");
    const [passwordCheckValidation, setPasswordCheckValidation] = useState(false);
    const [passwordCheckBlurred, setPasswordCheckBlurred] = useState(false);

    //Git Profile URL
    const [gitProfileUrl, setGitProfileUrl] = useState("");
    const [gitProfileUrlValidation, setGitProfileUrlValidation] = useState(false);
    const [gitProfileUrlBlurred, setGitProfileUrlBlurred] = useState(false);

    const handleIdCheck = async (e) => {

        e.preventDefault();

        if(!id) {
            setIdValidation(false);
            setIdValidationMessage("아이디를 입력하세요.");
            return;
        }

        try {
            const response = await checkId(id); 

            if (response.status === 200) {
                setIdValidation(true);
                setIdValidationMessage("사용 가능한 아이디입니다.");
            }
        } catch (error) {
            setIdValidation(false);
            setIdValidationMessage("이미 사용중인 아이디입니다.");
        }
    };

    const handleEmailCheck = async (e) => {

        e.preventDefault();

        if(!email) {
            setEmailValidation(false);
            setEmailValidationMessage("이메일을 입력하세요.");
            return;
        }

        if(!emailRegex.test(email)) {
            setEmailValidation(false);
            setEmailValidationMessage("이메일 형식이 올바르지 않습니다.");
            return;
        }

        try{
            const response = await emailCheck(email);

            if(response.status === 200) {
                setEmailValidation(true);
                setEmailValidationMessage("인증코드를 전송했습니다.");
            }
        }catch(error) {
            setEmailValidation(false);
            setEmailValidationMessage("이메일 전송에 실패했습니다.");
        }
    }

    const handleEmailCodeCheck = async (e) => {
        
        e.preventDefault();

        if(!emailCode) {
            setEmailCodeValidation(false);
            setEmailCodeValidationMessage("인증코드를 입력하세요.");
            return;
        }

        try{
            const response = await checkEmail(email, emailCode);

            if(response.status === 200) {
                setEmailCodeValidation(true);
                setEmailCodeValidationMessage("인증코드가 일치합니다.");
            }
        }catch(error) {
            setEmailCodeValidation(false);
            setEmailCodeValidationMessage("인증코드가 일치하지 않습니다.");
        }
    } 

    const handlePassword = (e) => {
        const value = e.target.value;
        setPassword(value);
        setPasswordValidation(passwordRegex.test(value));
    };

    const handlePasswordChange = (e) => {
        const value = e.target.value;
        setPasswordCheck(value);
        setPasswordCheckValidation(value === password);
    };

    const handleGitUrlChange = (e) => {
        const value = e.target.value;
        setGitProfileUrl(value);
        setGitProfileUrlValidation(gitProfileUrlRegex.test(value));
    };
    
    const handleBlur = (e, setBlurred) => {
        setBlurred(true);
    };

    // 회원가입시 아이디, 비밀번호, 이메일, Git Profile URL을 전달 모달로 표시

    return (
        <FormContainer>
            <Logo header="black" span="#F26F23" tag="h1" />
            <br />
            <br />

            {/* 아이디 */}
            <FormContainers>
                <input 
                    type="text" 
                    placeholder="아이디를 입력하세요." 
                    onChange={(e) => setId(e.target.value)}
                    disabled={idValidation}
                    />
                <button onClick={handleIdCheck}>중복 확인</button>
            </FormContainers>
            {idValidationMessage && (
                <p style={{ color: idValidation ? 'green' : 'red' }}>{idValidationMessage}</p>
            )}
    
            {/* 이메일 */}
            <FormContainers>
                <input 
                    type="text"
                    placeholder="이메일을 입력하세요."
                    onChange={(e) => setEmail(e.target.value)}
                    disabled={emailValidation}
                />
                <button onClick={handleEmailCheck}>이메일 인증</button>
            </FormContainers>
            {emailValidationMessage && (
                <p style={{ color: emailValidation ? 'green' : 'red' }}>{emailValidationMessage}</p>
            )}

            {/* 이메일 인증 코드 */}
            <FormContainers>
                <input 
                    type="text" 
                    placeholder="인증 코드를 입력하세요."
                    onChange={(e) => setEmailCode(e.target.value)}
                    disabled={emailCodeValidation}
                />
                    <button onClick={handleEmailCodeCheck}>확인</button>
            </FormContainers>
            {emailCodeValidationMessage && (
                    <p style={{ color: emailCodeValidation ? 'green' : 'red' }}>{emailCodeValidationMessage}</p>
            )}

            {/* 비밀번호 */}
            <input 
                type="password" 
                placeholder="비밀번호를 입력하세요."
                onChange={handlePassword}
                onBlur={(e) => handleBlur(e, setPasswordBlurred)}
            />
            {passwordBlurred && (
                passwordValidation ? 
                <p style={{ color: 'green' }}>올바른 비밀번호 입니다.</p> :
                <p style={{ color: 'red' }}>올바르지 않은 비밀번호 입니다.</p>
            )}

            {/* 비밀번호 확인 */}
            <input 
                type="password" 
                placeholder="비밀번호를 다시 입력하세요."
                onChange={handlePasswordChange}
                onBlur={(e) => handleBlur(e, setPasswordCheckBlurred)}
            />
            {passwordCheckBlurred && (
                password === passwordCheck ? 
                <p style={{ color: 'green' }}>비밀번호가 일치합니다.</p> :
                <p style={{ color: 'red' }}>비밀번호가 일치하지 않습니다.</p>
            )}
        
            {/* Git Profile URL */} 
            <input
                type="text"
                placeholder="Git 주소를 입력하세요."
                onChange={handleGitUrlChange}
                onBlur={(e) => handleBlur(e, setGitProfileUrlBlurred)}
            />
            {gitProfileUrlBlurred && (
                gitProfileUrlValidation ? 
                <p style={{ color: 'green' }}>올바른 Git 주소 입니다.</p> :
                <p style={{ color: 'red' }}>올바르지 않은 Git 주소 입니다.</p>
            )}
            <button>Sign Up</button>
        </FormContainer>
    );
};