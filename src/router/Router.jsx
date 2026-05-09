import { createBrowserRouter } from 'react-router'
import Landing from '../pages/landing/Landing.jsx'
import SignIn from '../pages/SignIn/SignIn.jsx'
import Sandbox from '../pages/sandbox/Sandbox.jsx'

const router = createBrowserRouter(
    [
        {
            path: '/',
            element: <Landing />,
        },
        {
            path: '/signin',
            element: <SignIn />,
        },
        {
            path: '/sandbox',
            element: <Sandbox />,
        },
    ],
    {
        basename: '/compunet-front',
    },
)

export default router;
