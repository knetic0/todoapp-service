import { Outlet, useNavigate } from "react-router";
import { useTokenCheckQuery } from "../hooks/use-query";
import { useEffect, useState } from "react";
import { BlockUI } from "primereact/blockui";
import { classNames } from "primereact/utils";

export default function HomeLayout() {
    const navigate = useNavigate();
    const [blockUI, setBlockUI] = useState<boolean>(true);

    const { isSuccess: isSuccessTokenCheck, isError: isErrorTokenCheck } = useTokenCheckQuery();

    useEffect(() => {
        if(isSuccessTokenCheck) {
            setBlockUI(false);
        }
    }, [isSuccessTokenCheck])

    useEffect(() => {
        if(isErrorTokenCheck) {
            setBlockUI(false);
            navigate({ pathname: '/' });
        }
    }, [isErrorTokenCheck])

    const blockUITemplate = () => {
        return <i className="pi pi-spin pi-spinner text-6xl"></i>
    }

    return (
        <BlockUI blocked={blockUI} fullScreen template={blockUITemplate} style={{ backgroundColor: "transparent"}}>
            <div className={classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden')}>
                <div className="flex flex-column align-items-center justify-content-center">
                    <div
                        className="border-round-2xl p-1"
                        style={{
                            background: 'linear-gradient(180deg, var(--surface-400) 10%, rgba(33, 150, 243, 0) 30%)'
                        }}>
                            <div className="w-full surface-card py-8 px-5 sm:px-8 border-round-3xl">
                                <Outlet />
                            </div>
                    </div>
                </div>
            </div>
        </BlockUI>
    )
}