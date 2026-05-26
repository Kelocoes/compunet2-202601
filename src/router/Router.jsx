import { createBrowserRouter } from "react-router";
import Login from "../pages/Login/Login";
import Dashboard from "../pages/Dashboard/Dashboard";
import Landing from "../pages/Landing/Landing";
import Register from "../pages/Register/Register";
import ProtectedRoute from "../components/ProtectedRoute";
import ContextPage from "../pages/Context/ContextPage";
import StoreContextPage from "../pages/StoreContext/StoreContextPage";
import UseContextPage from "../pages/UseContext/UseContextPage";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Landing />,
    },
    {
        path: "/props-context",
        element: <ContextPage />,
    },
    {
        path: "/use-context",
        element: <UseContextPage />,
    },
    {
        path: "/store-context",
        element: <StoreContextPage />,
    },
    {
        path: "/auth",
        children: [
            {
                index: true,
                element: <Login />,
            },
            {
                path: "login",
                element: <Login />,
            },
            {
                path: "rgister",
                element: <Register />,
            }
        ]
    },
    {
        element: <ProtectedRoute />,
        children: [
            {
                path: "dashboard",
                element: <Dashboard />,
            }
        ]
    }
], { basename: "/iaslab/compu2/profe" });

export default router;