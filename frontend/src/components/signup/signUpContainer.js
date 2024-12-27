import { Logo } from "../../style/logo";
import styled from "styled-components";
import { useEffect, useState } from "react";
import { checkId, join } from "../../api/authApi";
import { emailCheck,checkEmail } from "../../api/mailApi"; 
import Modal from "../../util/modal";
import { Policy } from "../../assets/policy";
import { useNavigate } from "react-router-dom";

const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 33%;
    gap: 20px;
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



export const SignUpContainer = () => {

    const navigate = useNavigate(); 

    // 정규식
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    const gitProfileUrlRegex = /^https:\/\/github\.com\/[\w-]+$/;

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

    //Git Profile URL
    const [gitProfileUrl, setGitProfileUrl] = useState("");
    const [gitProfileUrlValidation, setGitProfileUrlValidation] = useState(false);
    const [gitProfileUrlBlurred, setGitProfileUrlBlurred] = useState(false);

    // 이름 임시용 나중에 테이불에 수정 필요
    const [name, setName] = useState("");
    const [nameValidation, setNameValidation] = useState(false);

    // 모달
    const [modal, setModal] = useState(false);
    const [modalContent, setModalContent] = useState();   
    const [confirm, setConfirm] = useState();
    const [close, setClose] = useState();
    const [confirmStatus, setConfirmStatus] = useState(false);
    const [closeStatus , setCloseStatus] = useState(false);
    

    const routeHome = () => {
        navigate("/");
    }

    const closeModal = () => {
        setModal(false);
    }

    // 초기 로딩 시 모달창 띄우기 및 세팅
    useEffect(() => {

        setModalContent(<Policy/>);
        setConfirmStatus(true);
        setCloseStatus(true);
        setConfirm(()=>closeModal);
        setClose(()=>routeHome);
        const timer = setTimeout(() => {
            setModal(true); 
        }, 30); 

        return () => clearTimeout(timer);
        
    },[]);

    // 유효성 검사 함수 
    const validate = async(value, e) => {

        e.preventDefault();

        switch(value) {
            case "id":
                if(id === "" || id === null) {
                    setIdValidation(false); 
                    setIdValidationMessage("아이디를 입력하세요.");
                    return;
                }else{
                    try{
                        const response = await checkId(id);
                        if(response.status === 200) {
                            console.log(response);
                            setIdValidation(true);
                            setIdValidationMessage("사용 가능한 아이디입니다.");
                        }
                    }catch(error) {
                        setIdValidation(false);
                        setIdValidationMessage("이미 사용중인 아이디입니다.");
                    }
                }
                break;
            case "email":
                if(email === "" || email === null) {
                    setEmailValidation(false);
                    setEmailValidationMessage("이메일을 입력하세요.");
                    return;
                }else{
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
                break;
            case "emailCode":
                if(emailCode === "" || emailCode === null) {
                    setEmailCodeValidation(false);
                    setEmailCodeValidationMessage("인증코드를 입력하세요.");
                    return;
                }else{
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
                break;
            case "password":
                if(!passwordRegex.test(password)) {
                    setPasswordBlurred(true);
                    setPasswordValidation(false);
                    return;
                }
                setPasswordBlurred(true);
                setPasswordValidation(true);
                break;
            case "gitProfileUrl": 
                if(!gitProfileUrlRegex.test(gitProfileUrl)) {
                    setGitProfileUrlBlurred(true);
                    setGitProfileUrlValidation(false);
                    return;
                }
                setGitProfileUrlBlurred(true);
                setGitProfileUrlValidation(true);
                break;
            case "name":
                setName(e.target.value);
                if(name !== "" || name !== null) {
                    setNameValidation(true);
                    return;
                }
                break;
            
            default:
                break;
        }
    }

    // 회원가입 함수    
    const joinMember = async(e) => {

        e.preventDefault();

        if(!idValidation || !emailValidation || !emailCodeValidation || !passwordValidation || !gitProfileUrlValidation || !nameValidation) {
            setModal(true);
            setModalContent(<p>입력값을 확인해주세요.</p>);
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(()=>closeModal);
            return;
        }   

        const member = {
            memberId : id,
            email : email,
            memberPwd : password,
            memberName : name,
            gitProfileUrl : gitProfileUrl
        }

        console.log(member);
        try{
            const response = await join(member);
            if(response.status === 200) {
                setModal(true);
                setModalContent(<p>회원가입 성공.</p>);
                setConfirmStatus(true);
                setCloseStatus(false);
                setConfirm(()=>routeHome);
            }
        }catch(error) {
            setModal(true);
            setModalContent(<p>서버 오류</p>);
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(()=>closeModal); 
        }
    }

    return (
        <>
        <FormContainer>
            <Logo header="black" span="#F26F23" tag="h1" />
            <br />

            {/* 아이디 */}
            <FormContainers>
                <input 
                    type="text" 
                    placeholder="아이디를 입력하세요." 
                    onChange={(e) => setId(e.target.value)}
                    disabled={idValidation}
                    value={id}
                />
                <button onClick={(e)=>validate("id",e)}>중복 확인</button>
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
                    value={email}
                />
                <button onClick={(e)=>validate("email",e)}>이메일 인증</button>
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
                    value={emailCode}
                />
                    <button onClick={(e)=>validate("emailCode",e)}>확인</button>
            </FormContainers>
            {emailCodeValidationMessage && (
                    <p style={{ color: emailCodeValidation ? 'green' : 'red' }}>{emailCodeValidationMessage}</p>
            )}

            {/* 비밀번호 */}
            <input 
                type="password" 
                placeholder="비밀번호를 입력하세요."
                onChange={(e) => setPassword(e.target.value)}
                value={password}
                onBlur={(e) => validate("password", e)}
            />
            {passwordBlurred && (
                passwordValidation ? 
                <p style={{ color: 'green' }}>올바른 비밀번호 입니다.</p> :
                <p style={{ color: 'red' }}>숫자, 영문자, 특수문자를 포함한 8~25자리로 입력해주세요</p>
            )}
        
            {/* Git Profile URL */} 
            <input
                type="text"
                placeholder="Git 주소를 입력하세요."
                onChange={(e) => setGitProfileUrl(e.target.value)}
                value={gitProfileUrl}
                onBlur={(e) => validate("gitProfileUrl", e)}
            />
            {gitProfileUrlBlurred && (
                gitProfileUrlValidation ? 
                <p style={{ color: 'green' }}>올바른 Git 주소 입니다.</p> :
                <p style={{ color: 'red' }}>올바르지 않은 Git 주소 입니다.</p>
            )}

            <input
                type="text"
                placeholder="이름을 입력하세요."
                onChange={(e) => validate("name", e)}
                value={name}
            />
            <button onClick={joinMember}>Sign Up</button>
        </FormContainer>
        <Modal 
            open={modal} 
            children={modalContent} 
            confirmStatus={confirmStatus} 
            closeStatus={closeStatus}
            confirm={confirm}
            close={close}
        />
        </>
    );
};