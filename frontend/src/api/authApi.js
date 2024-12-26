import { instance } from "../config/axiosConfig"

// 아이디 중복 체크 
export const checkId = (id) => {
    return instance.post(
        `/checkId?memberId=${id}`
    )
}

// 회원 가입 
export const join = (id, password, name, email) => {
    return instance.post(
        '/join'
        , { 
            memberId : id, 
            memberPwd : password,
            memberName : name,
            email : email
        }
    )
}

// 아이디 찾기
export const findId = async (email) => {
    return instance.post(
        '/findId'
        , { email : email }
    )
}   

// 비밀번호 찾기
export const findPwd = async (id, email) => {    
    return instance.post(
        '/findPwd'
        , { 
            memberId : id, 
            email : email 
        }
    )
}