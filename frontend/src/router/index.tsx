import { BrowserRouter, Route, Routes } from "react-router";
import AuthLayout from "../layout/auth-layout";
import Login from "../views/login";
import Register from "../views/register";
import HomeLayout from "../layout/home-layout";
import Home from "../views/home";

const Router = () => {
    return (
        <BrowserRouter basename={process.env.PUBLIC_URL}>
            <Routes>
                <Route element={<AuthLayout />}>
                    <Route path="/" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                </Route>
                <Route element={<HomeLayout />}>
                    <Route path="/home" element={<Home />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default Router;