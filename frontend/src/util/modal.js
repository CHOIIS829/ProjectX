import styled from "styled-components";

const ModalContainer = styled.div`
    position: fixed; 
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 2;
    background-color: rgba(0, 0, 0, 0.6);
    display: flex;
    opacity: 1;
    opacity: ${props => (props.open ? 1 : 0)};
    pointer-events: ${props => (props.open ? 'auto' : 'none')};
    transition: opacity 0.3s ease-in;
    align-items: center; 
    section {
        width: 90%;
        max-width: 400px;
        text-align: center;   
        margin: 0 auto;
        border-radius: 0.3rem;
        overflow: hidden;
    }
    section > header {
        position: relative;
        padding: 10px 10px 10px;
        background-color: var(--main-color);
        display: flex;
        justify-content: center;
    }
    section > header > p {
        margin: 0px;
        color: white;
        font-weight: bolder;
    }
    section > main {
        padding: 30px;
        font-size: 15px;
        background-color: white;
        max-height: 400px;
        overflow-y: scroll;
    }
    section > main::-webkit-scrollbar{
        width: 5px;
    }
    section > footer {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: white;
        padding: 15px 15px 15px;
        gap: 30px;
    }
    section > footer > button {
        border: none;
        outline: none;
        font-size: 15px;
        font-weight: bold;
        background-color: transparent;
        color: black;
        cursor: pointer;
    }
`;

const Modal = (props) => {
    
    const {open, confirmStatus, confirm, closeStatus, close, children} = props;

    /*
    * Moddal 컴포넌트 사용방법
    * open: 모달창을 열지 닫을지 결정하는 상태
    * confirmStatus: 확인 버튼을 누를 수 있는 상태
    * confirm: 확인 버튼을 눌렀을 때 실행되는 함수
    * closeStatus: 취소 버튼을 누를 수 있는 상태
    * close: 취소 버튼을 눌렀을 때 실행되는 함수
    * children: 모달창에 표시할 내용 
    */

    return(
        <ModalContainer open={open}>
            <section open={open}>
                <header>
                    <p>project<span style={{color : 'black'}}>X</span></p>
                </header>
                <main>
                    {children}
                </main>
                <footer>
                    {confirmStatus && <button onClick={confirm}>확인</button>}
                    {closeStatus && <button onClick={close}>취소</button>}
                </footer>
            </section>
        </ModalContainer>
    )
};

export default Modal;