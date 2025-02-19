import axios, { AxiosInstance } from "axios";

const baseURL : string | undefined = process.env.REACT_APP_API_BASE_URL;
const securityKey : string | undefined = process.env.REACT_APP_API_SECURITY_KEY;

export const api : AxiosInstance = axios.create({
    baseURL: baseURL,
})

class Axios {
    static setSecurityKey() {
        Axios.setHeader('X-Security-Key', securityKey || "");
    }

    static setAuthorizationToken(token:string) {
        localStorage.setItem('token', token);
        Axios.setHeader('Authorization', `Bearer ${token}`);
    }

    static setHeader(name: string, value: string) {
        api.defaults.headers.common[name] = value;
    }

    static getAuthToken() {
        return localStorage.getItem('token');
    }
}

export default Axios;