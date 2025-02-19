import { Button } from "primereact/button"
import { InputText } from "primereact/inputtext";
import { Password } from "primereact/password";
import { useEffect, useState } from "react";
import { useLoginMutation } from "../hooks/use-query";
import { useNavigate } from "react-router";
import { useToast } from "../context/toast-context";

const Login = () => {
    const navigate = useNavigate();
    const { showToast } = useToast();

    const {
        mutate: loginMutation,
        data: loginData,
        isError: isErrorLoginMutation,
        isSuccess: isSuccessLoginMutation,
        isPending: isPendingLoginMutation,
    } = useLoginMutation();

    const [credentials, setCredentials] = useState({
        username: "",
        password: ""
    });

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setCredentials({
            ...credentials,
            [name]: value
        });
    }

    const checkCredentials = () => {
        if (credentials.username === "" || credentials.password === "") {
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            loginMutation(credentials);
        }
    }

    useEffect(() => {
        if(isSuccessLoginMutation) {
            if(loginData?.success) {
                navigate({ pathname: '/home' });
                showToast({ severity: 'success', summary: 'Success', detail: 'Login success! Redirecting to home page...' });
            }else {
                showToast({ severity: 'error', summary: 'Error', detail: loginData.message });
            }
        }
    }, [isSuccessLoginMutation])

    useEffect(() => {
        if(isErrorLoginMutation) {
            showToast({ severity: 'error', summary: 'Error', detail: 'An error occured' });
        }
    }, [isErrorLoginMutation])

    return (
        <>
            <div className="text-center mb-5">
                <div className="text-900 text-3xl font-medium mb-3">Welcome!</div>
                <span className="text-600 font-medium">Sign in to continue</span>
            </div>
            <div className="flex flex-column">
                <label htmlFor="username" className="block text-900 text-xl font-medium mb-2">
                    Username
                </label>
                <InputText name="username" value={credentials.username} onChange={handleChange} id="username" type="text" placeholder="Username" className="w-full md:w-30rem mb-5 p-3" />
                <label htmlFor="password" className="block text-900 font-medium text-xl mb-2">
                    Password
                </label>
                <Password name="password" value={credentials.password} onChange={handleChange} id="password" toggleMask placeholder="Password" className="w-full mb-5" inputClassName="w-full p-3 md:w-30rem" />
                <Button loading={isPendingLoginMutation} severity="secondary" label="Sign In" className="w-full p-3 text-xl" onClick={handleSubmit} />
            </div>
            <span className='font-medium flex justify-content-center align-items-center mt-4 gap-2' style={{color:"#2D3967"}}>
                Dont have account? 
                <a href='/register' style={{color:"#2D3967"}} className='font-semibold'>
                    Sign up
                </a>
            </span>
        </>
    )
}

export default Login;