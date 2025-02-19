import Axios, { api } from "../axios";
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse, TodoCreateRequest, TodoCreateResponse, TokenCheckResponse } from "../types";

class Service {
    static async login(request: LoginRequest): Promise<LoginResponse> {
        Axios.setSecurityKey();
        const response = await api.post('/user/login', request);
        const { data } : { data: LoginResponse } = response;
        if(data.success) {
            Axios.setAuthorizationToken(data.data.token);
        }
        return data;
    }

    static async register(request: RegisterRequest): Promise<RegisterResponse> {
        Axios.setSecurityKey();
        const response = await api.post('/user/create', request);
        return response.data;
    }

    static async tokenCheck(): Promise<TokenCheckResponse> {
        Axios.setAuthorizationToken(Axios.getAuthToken() || "");
        const response = await api.post('/api/auth/authenticate');
        return response.data;
    }

    static async createTodo(request: TodoCreateRequest): Promise<TodoCreateResponse> {
        const response = await api.post('/api/auth/todo/create', request);
        return response.data;
    }
}

export default Service;