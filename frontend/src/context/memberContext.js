import { createContext } from 'react';

// 해당 context 수정 필요 및 react-query 사용 필요
const MemberContext = createContext({
    member :{
        memberId: "",
        memberName: "",
        email: "",
    },
    // PK 값 으로 회원 조회 할건지 아니면 아아디(unique 값이라) 
    // 로그인 하면 ->  저 값들이 들어옴
    // 로그인 판단 여부 가 안됨
    isLoggedIn: false,
    setLoggedUser: () => {},
    setLoggedOut: () => {}
});

export default MemberContext;
