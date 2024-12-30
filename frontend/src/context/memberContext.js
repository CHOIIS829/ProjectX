import { createContext } from 'react';

// 해당 context 수정 필요 및 react-query 사용 필요
const MemberContext = createContext({
    member :{
        memberId: "",
        memberPwd: "",
        memberName: "",
        email: "",
    },
    isLoggedIn: false,
    setLoggedUser: () => {},
    setLoggedOut: () => {}
});

export default MemberContext;