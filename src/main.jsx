import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import Landing from './Landing';
import ProfileCard from './components/ProfileCard';
import { RouterProvider } from 'react-router';
import router from './router/Router';

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <RouterProvider router={router}></RouterProvider>
    </StrictMode>,
);
