import styled from "styled-components";
import { Logo } from "../../style/logo";
import { filterRoutes } from "../../assets/routeData";
import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Modal from "../../util/modal";
import { findId } from "../../api/authApi";

const FormContainer = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 45px;
    padding: 100px 60px;
    border: 1px solid lightgrey;
    border-radius: 10px;
    width: 21%;
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

export const FindIdContainer = () => {

    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [email, setEmail] = useState(''); 

    // 모달 세팅
    const [modal, setModal] = useState(false);
    const [modalContent, setModalContent] = useState();   
    const [confirm, setConfirm] = useState();
    const [close, setClose] = useState();
    const [confirmStatus, setConfirmStatus] = useState(false);
    const [closeStatus , setCloseStatus] = useState(false);

    const makeRoutes = () => {
        const filteredRoutes = filterRoutes('/signup', '/login','/find-password');
        return filteredRoutes.map((route, index) => {
            return (
                <React.Fragment key={route.path}>
                    <p onClick={() => navigate(route.path)}>{route.titleKorean}</p>
                    {index < filteredRoutes.length - 1 && <span>|</span>}
                </React.Fragment>
            );
        });
    };

    const closeModal = () => {
        setModal(false);
    }

    const goToLogin = () => {
        navigate('/login');
    }

    const handleFindId = async (e) => {

        e.preventDefault();

        if(name === '' || email === ''){
            setModalContent('이름과 이메일을 입력해주세요.');
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(()=>closeModal);
            setModal(true);
            return;
        }

        if(!email.includes('@')){
            setModalContent('이메일 형식이 올바르지 않습니다.');
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(()=>closeModal);
            setModal(true);
            return;
        }

        try{
            const data = {
                memberName: name,
                email: email
            };

            const response = await findId(data);
            if(response.status === 200){
                setModalContent("아이디는 : " + response.data.data.memberId);
                setConfirmStatus(true);
                setCloseStatus(false);
                setConfirm(()=>closeModal);
                setModal(true);
                return;
            }
        }catch(error){
            setModalContent('이름과 이메일을 확인해주세요.');
            setConfirmStatus(true);
            setCloseStatus(false);
            setConfirm(()=>closeModal);
            setModal(true);
            return;
        }
    };

    return(
        <FormContainer>
            <Logo header="black" span="#F26F23" tag="h1"/>
            <input 
                type="text" 
                placeholder="이름을 입력해주세요."
                onChange={(e) => setName(e.target.value)}
                value={name}
            />
            <input 
                type="text" 
                placeholder="이메일을 입력해주세요." 
                onChange={(e) => setEmail(e.target.value)}
                value={email}
            />
            <ControlContainer>
                {makeRoutes()}
            </ControlContainer>
            <button onClick={(e)=>handleFindId(e)}>이메일 찾기</button>
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
}