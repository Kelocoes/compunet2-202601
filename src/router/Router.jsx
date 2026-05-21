import { createBrowserRouter } from "react-router";
import Login from "../pages/Login/Login";
import Dashboard from "../pages/Dashboard/Dashboard";
import Landing from "../pages/Landing/Landing";
import Register from "../pages/Register/Register";
import ProfileCard from "../components/ProfileCard";

const router = createBrowserRouter([
    {
        path: "/", // @RequestMapping("/")
        element: <ProfileCard />,
    },
    {
        path: "/dashboard",
        element: <Dashboard />,
    },
    {
        path: "/auth",
        children: [
            {
                element: <Login />,
                index: true,
            },
            {
                path: "login",
                element: <Login />,
            },
            {
                path: "register",
                element: <Register />,
            }
        ]
    }
], { basename: "/compu2" });

export default router;