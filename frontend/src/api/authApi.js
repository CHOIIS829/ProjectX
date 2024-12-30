import { instanceNoToken } from "../config/axiosConfig"

// 아이디 중복 체크 
export const checkId = (id) => {
    return instanceNoToken.post(
        `/checkId?memberId=${id}`
    )
}

// 이메일 인증 코드 전송
export const sendEmail = (email) => {  
    return instanceNoToken.post(
        `/sendEmail?email=${email}`
    )
}

// 이메일 인증 코드 확인
export const checkEmail = (email, code) => {
    return instanceNoToken.post(
        '/checkEmail',
        {
            email : email,
            code : code
        }
    )
}

// 회원가입
export const join = (member) => {
    return instanceNoToken.post(
        '/join', member
    )
}

// 아이디 찾기
export const findId = (member) => {
    return instanceNoToken.post(
        '/findId', member
    )
}   

// 비밀번호 찾기
export const findPwd = (member) => {
    return instanceNoToken.post(
        '/findPw', member
    )
}

// 로그인
export const login = (member) => {
    return instanceNoToken.post(
        '/login', member
    )
}   