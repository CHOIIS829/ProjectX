import axios, { Axios } from "axios";

export const instanceNoToken = axios.create({
    baseURL: process.env.REACT_APP_API_URL,  
    timeout: 10000,
    headers: {
        "Content-Type": "application/json",
    },
});

export const instanceToken = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
    timeout: 10000,
    headers: {
        "Content-Type": "application/json"
    }
});

instanceToken.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("aceesstoken");
        if (token) {
            const newConfig = {...config};
            newConfig.headers.Authorization = token;
            return newConfig;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

instanceToken.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response.status === 401) {
            localStorage.removeItem("accesstoken");
            window.location.href = "/login";
        }
        return Promise.reject(error);
    }   
);