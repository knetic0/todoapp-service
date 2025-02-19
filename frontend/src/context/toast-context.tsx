import { Toast } from "primereact/toast";
import { createContext, useContext, useRef } from "react"

interface ToastContextProps {
    showToast: (options: any) => void;
}

const ToastContext = createContext<ToastContextProps | undefined>(undefined);

export const ToastContextProvider = ({ children }: { children: React.ReactNode }) => {
    const toastRef = useRef<any>(null);

    const showToast = (options: any) => {
        if(!toastRef.current) {
            return;
        }
        toastRef.current.show(options);
    }

    return (
        <ToastContext.Provider value={{ showToast }}>
            {children}
            <Toast ref={toastRef}>
                {children}
            </Toast>
        </ToastContext.Provider>
    )
}

export const useToast = () => {
    const context = useContext(ToastContext);
    if (!context) {
        throw new Error("useToast must be used within a ToastContextProvider");
    }
    return context;
}