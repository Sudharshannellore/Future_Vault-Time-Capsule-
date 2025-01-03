import { createBrowserRouter } from "react-router-dom";
import Home from "./pages/Home";
import Register from "./forms/Register";
import Login from "./forms/Login";
import CapsuleDashboard from "./pages/CapsuleDashboard";

const Router = createBrowserRouter([

    {
        path : '/',
        element : <Home/>
    },
    {
        path : '/register',
        element : <Register/>
    },
    {
        path : '/login',
        element : <Login/>
    },
    {
        path : '/capsule',
        element : <CapsuleDashboard/> 
    }

])
export default Router