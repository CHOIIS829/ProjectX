import { instance } from "../config/axiosConfig"

// 아이디 중복 체크 
export const checkId = (id) => {
    return instance.post(
        `/checkId?memberId=${id}`
    )
}

// 이메일 인증 코드 전송
export const sendEmail = (email) => {  
    return instance.post(
        `/sendEmail?email=${email}`
    )
}

// 이메일 인증 코드 확인
export const checkEmail = (email, code) => {
    return instance.post(
        '/checkEmail',
        {
            email : email,
            code : code
        }
    )
}

// 회원가입
export const join = (member) => {
    return instance.post(
        '/join', member
    )
}