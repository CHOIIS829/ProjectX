export const RouteData = {
    main: {
        titleEnglish: "Main",
        titleKorean: "메인",
        path: "/"
    },
    login: {
        titleEnglish: "Log In",
        titleKorean: "로그인",
        path: "/login"
    },
    signup: {
        titleEnglish: "Sign Up",
        titleKorean: "회원가입",
        path: "/signup"
    },
    findEmail: {
        titleEnglish: "Find Email",
        titleKorean: "이메일 찾기",
        path: "/find-email"
    },
    findPassword: {
        titleEnglish: "Find Password",
        titleKorean: "비밀번호 찾기",
        path: "/find-password"
    }
};

/*
    라우터 데이터 필터링 함수
    - data: RouteData 객체
    - paths: 필터링할 path 문자열
    - 필터링된 데이터 반환
*/
export const filterRoutes = (...paths) => {
    return Object.values(RouteData).filter(route => {
        return paths.includes(route.path);
    });
};

/*
    라우터 데이터 반환 함수
    - 고민 필요
*/