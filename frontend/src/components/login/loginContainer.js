import styled from "styled-components";
import React, { useState } from "react";
import { Logo } from "../../style/logo";
import { useNavigate } from "react-router-dom";
import { filterRoutes } from "../../assets/routeData"; 
import Modal from "../../util/modal";   
import { login } from "../../api/authApi";
import MemberContext from "../../context/memberContext";

const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 45px;
    padding: 100px 60px;
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
            color: black;
        }
    }
`;

const ControlContainer = styled.div`
    display: flex;
    gap: 10px;
    p {
        cursor: pointer;
        font-size: medium;
    }
    p:nth-child(odd):hover {
        color: var(--main-color);
    }
`;


export const LoginContainer = () => {
    
    const navigate = useNavigate();

    const [id, setId] = useState('');
    const [password, setPassword] = useState(''); 

    // 모달 세팅
    const [modal, setModal] = useState(false);
    const [modalContent, setModalContent] = useState();   
    const [confirm, setConfirm] = useState();
    const [close, setClose] = useState();
    const [confirmStatus, setConfirmStatus] = useState(false);
    const [closeStatus , setCloseStatus] = useState(false);

    const makeRoutes = () => {
        const filteredRoutes = filterRoutes('/find-id', '/signup', '/find-password');
        return filteredRoutes.map((route, index) => {
            return (
                <React.Fragment key={route.path}>
                    <p onClick={() => navigate(route.path)}>{route.titleKorean}</p>
                    {index < filteredRoutes.length - 1 && <span>|</span>}
                </React.Fragment>
            );
        });
    };

    const handleLogin = async(e) => {
        
        e.preventDefault();
        
        if(id === '' || password === '') {
            setModalContent('아이디와 비밀번호를 입력해주세요.');
            setModal(true);
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(() => () => setModal(false));
            return;
        }
        
        try{
            const data = {
                memberId : id,
                memberPwd : password
            }

            const response = await login(data);
            if(response.status === 200){
                localStorage.setItem('accesstoken', response.headers.authorization); 
                
                // 로그인 요청시 사용자 정보를 받아와서 context에 저장

                navigate('/');
            }

        }catch(error){
            setModalContent('아이디와 비밀번호를 확인해주세요.');
            setModal(true);
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(() => () => setModal(false));
        }
    };  

    return (
        <FormContainer>
            <Logo header="black" span="#F26F23" tag="h1"/>
            <input 
                type="text" 
                placeholder="아이디를 입력하세요."
                onChange={e => setId(e.target.value)}
                value={id} 
            />
            <input 
                type="password" 
                placeholder="비밀번호를 입력하세요." 
                onChange={e => setPassword(e.target.value)}
                value={password}
            />
            <ControlContainer>
                {makeRoutes()}
            </ControlContainer>
            <button onClick={(e)=>handleLogin(e)}>로그인</button>
            <Modal 
                open={modal} 
                children={modalContent} 
                confirmStatus={confirmStatus} 
                closeStatus={closeStatus}
                confirm={confirm}
                close={close}
            />
        </FormContainer>
    );
};