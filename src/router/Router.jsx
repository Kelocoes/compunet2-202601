import { createBrowserRouter } from "react-router";
import Login from "../pages/Login/Login";
import Dashboard from "../pages/Dashboard/Dashboard";
import Landing from "../pages/Landing/Landing";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Landing />,
    },
    {
        path: "/login",
        element: <Login />,
    },
    {
        path: "/dashboard",
        element: <Dashboard />,
    },
], { basename: "/compu2" });

export default router;