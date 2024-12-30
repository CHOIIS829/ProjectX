import { instanceNoToken } from "../config/axiosConfig";

// 이메일 인증
export const emailCheck = (email) => {
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