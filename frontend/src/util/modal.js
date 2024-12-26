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
    opacity: ${props => (props.open ? 1 : 0)};
    pointer-events: ${props => (props.open ? 'auto' : 'none')};
    transition: opacity 0.5s ease-in;
    align-items: center; 

`;

export const Modal = (props) => {
    
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
        <ModalContainer>
            <section>
                <header open={open}>
                    <p>projectX</p>
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