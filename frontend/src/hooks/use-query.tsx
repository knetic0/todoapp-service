import { useMutation, useQuery } from "@tanstack/react-query";
import { LoginRequest, RegisterRequest, TodoCreateRequest } from "../types";
import Service from "../services";

export const useLoginMutation = () =>
    useMutation({
        mutationFn: (request: LoginRequest) => Service.login(request),
    })

export const useRegisterMutation = () =>
    useMutation({
        mutationFn: (request: RegisterRequest) => Service.register(request),
    })

export const useTokenCheckQuery = () =>
    useQuery({
        queryKey: ["tokenCheck"],
        queryFn: () => Service.tokenCheck(),
    })

export const useCreateTodoMutation = () =>
    useMutation({
        mutationFn: (request: TodoCreateRequest) => Service.createTodo(request),
    })