import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import React, { useEffect, useState } from "react";
import { RegisterRequest } from "../types";
import { useRegisterMutation } from "../hooks/use-query";
import { useNavigate } from "react-router";
import { useToast } from "../context/toast-context";

export default function Register() {
    const navigate = useNavigate();
    const { showToast } = useToast();

    const {
        mutate: registerMutation,
        data: registerData,
        isError: isErrorRegisterMutation,
        isSuccess: isSuccessRegisterMutation,
        isPending: isPendingRegisterMutation,
    } = useRegisterMutation();

    const [credentials, setCredentials] = useState<RegisterRequest>({
        username: "",
        password: "",
        email: "",
        firstName: "",
        lastName: ""
    });

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setCredentials({
            ...credentials,
            [name]: value
        });
    }

    const checkCredentials = () => {
        if (credentials.username  === "" || 
            credentials.password  === "" || 
            credentials.email     === "" || 
            credentials.firstName === "" || 
            credentials.lastName  === ""
        ) {
            return false;
        }
        return true
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            registerMutation(credentials);
        }
    }

    useEffect(() => {
        if(isSuccessRegisterMutation) {
            if(registerData?.success) {
                navigate({ pathname: '/' });
                showToast({ severity: 'success', summary: 'Success', detail: registerData.message });
            } else{
                showToast({ severity: 'error', summary: 'Error', detail: registerData.message });
            }
        }
    }, [isSuccessRegisterMutation])

    useEffect(() => {
        if(isErrorRegisterMutation) {
            showToast({ severity: 'error', summary: 'Error', detail: 'An error occured while processing your request' });
        }
    }, [isErrorRegisterMutation])

    return (
        <>
            <div className="text-center mb-5">
                <div className="text-900 text-3xl font-medium mb-3">Welcome!</div>
                <span className="text-600 font-medium">Sign up to continue</span>
            </div>
            <div className="flex flex-column">
            <label htmlFor="firstName" className="block text-900 text-xl font-medium mb-2">
                    First Name
                </label>
                <InputText name="firstName" value={credentials.firstName} onChange={handleChange} id="firstName" type="text" placeholder="first Name" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="lastName" className="block text-900 text-xl font-medium mb-2">
                    Last Name
                </label>
                <InputText name="lastName" value={credentials.lastName} onChange={handleChange} id="lastName" type="text" placeholder="lastName" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="username" className="block text-900 text-xl font-medium mb-2">
                    Username
                </label>
                <InputText name="username" value={credentials.username} onChange={handleChange} id="username" type="text" placeholder="Username" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="email" className="block text-900 text-xl font-medium mb-2">
                    Email
                </label>
                <InputText name="email" value={credentials.email} onChange={handleChange} id="email" type="text" placeholder="email" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="password" className="block text-900 font-medium text-xl mb-2">
                    Password
                </label>
                <Password name="password" value={credentials.password} onChange={handleChange} id="password" toggleMask placeholder="Password" className="w-full mb-5" inputClassName="w-full p-3 md:w-30rem" />
                <Button loading={isPendingRegisterMutation} severity="secondary" label="Sign In" className="w-full p-3 text-xl" onClick={handleSubmit} />
            </div>
            <span className='font-medium flex justify-content-center align-items-center mt-4 gap-2' style={{color:"#2D3967"}}>
                You have account? Please 
                <a href='/' style={{color:"#2D3967"}} className='font-semibold'>
                    Sign in
                </a>
            </span>
        </>
    )
}