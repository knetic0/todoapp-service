import { Button } from "primereact/button";
import { FloatLabel } from "primereact/floatlabel";
import { InputText } from "primereact/inputtext";
import { TodoCreateRequest } from "../types";
import { useEffect, useState } from "react";
import { Editor } from "primereact/editor";
import { Calendar } from "primereact/calendar";
import { useCreateTodoMutation } from "../hooks/use-query";
import { useToast } from "../context/toast-context";
import { TabPanel, TabView } from "primereact/tabview";

export default function Home() {
    const [credentials, setCredentials] = useState<TodoCreateRequest>({
        title: "",
        content: "",
        dueTime: new Date()
    });

    const { showToast } = useToast();

    const {
        mutate: createTodoMutation,
        data: createTodoData,
        isError: isErrorCreateTodoMutation,
        isSuccess: isSuccessCreateTodoMutation,
        isPending: isPendingCreateTodoMutation,
    } = useCreateTodoMutation();

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setCredentials({
            ...credentials,
            [name]: value
        });
    }

    const handleDueTimeChange = (e: any) => {
        setCredentials({
            ...credentials,
            dueTime: e.value
        });
    }

    const handleContentChange = (e: any) => {
        setCredentials({
            ...credentials,
            content: e.htmlValue
        });
    }

    const checkCredentials = () => {
        if (credentials.title === "" || credentials.content === "") {
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if(checkCredentials()) {
            createTodoMutation(credentials);
        }
    }

    useEffect(() => {
        if(isSuccessCreateTodoMutation) {
            showToast({ severity: 'success', summary: 'Success', detail: 'Todo created successfully!' });
        }
    }, [isSuccessCreateTodoMutation])

    useEffect(() => {
        if(isErrorCreateTodoMutation) {
            showToast({ severity: 'error', summary: 'Error', detail: 'An error occured' });
        }
    }, [isErrorCreateTodoMutation])

    return (
        <TabView>
            <TabPanel header="Create Todo">
                <div className="flex flex-column gap-3 p-3">
                    <div className="flex w-full gap-3">
                        <FloatLabel>
                            <InputText className="flex-grow-1" id="title" name="title" value={credentials.title} onChange={handleChange} />
                            <label htmlFor="title">Enter Title</label>
                        </FloatLabel>
                        <Calendar className="flex-grow-1" value={credentials.dueTime} onChange={handleDueTimeChange} showWeek minDate={new Date()} />
                    </div>
                    <div className="w-full">
                        <Editor value={credentials.content} onTextChange={handleContentChange} style={{ height: '320px' }} />
                    </div>
                    <div className="w-full">
                    </div>
                    <Button className="w-full" icon="pi pi-plus" label="Create Todo" severity="contrast" onClick={handleSubmit} loading={isPendingCreateTodoMutation} />
                </div>
            </TabPanel>
            <TabPanel header="View Todos">
                <div className="p-3">
                    
                </div>
            </TabPanel>
        </TabView>
    )
}