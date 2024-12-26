import { instance } from "../config/axiosConfig";

// 이메일 인증
export const emailCheck = (email) => {
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