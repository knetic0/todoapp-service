interface Result {
    success: boolean;
    message: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse extends Result {
    data: {
        token: string
    }
}

export interface RegisterRequest {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    username: string;
}

export interface RegisterResponse extends Result { }

export interface TokenCheckResponse extends Result {}

export interface TodoCreateRequest {
    title: string;
    content: string;
    dueTime: Date;
}

export interface TodoCreateResponse extends Result { }