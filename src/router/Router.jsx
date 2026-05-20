import { createBrowserRouter } from "react-router";
import ProfileCard from "../components/ProfileCard";

const router = createBrowserRouter([
    {
        path: "/",
        element: <h1>Pagina principal</h1>,
    },
    {
        path: "/login",
        element: <h1>Login del usuario</h1>,
    },
    {
        path: "/dashboard",
        element: <ProfileCard name="Kevin" likes={10} />,
    },
], { basename: "/compu2" });

export default router;