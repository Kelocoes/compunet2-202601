import axios from 'axios';

const baseURL = import.meta.env.VITE_API_HOST || 'http://localhost:8081/compunet2-2026';

const axiosClient = axios.create({
    baseURL,
    headers: {
        'Content-Type': 'application/json',
    },
});

axiosClient.interceptors.request.use(
    (config) => {
        try {
            const token = localStorage.getItem('token');
            if (token) {
                config.headers = config.headers || {};
                config.headers.Authorization = `Bearer ${token}`;
            }
        } catch (e) {
            // Si falla el acceso, tiremos un error o simplemente continuamos sin token
            console.error('Error accessing localStorage for token:', e);
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export async function safeRequest(request) {
    try {
        const response = await request;
        return {
            error: false,
            data: response.data,
            status: response.status,
        };
    } catch (error) {
        if (axios.isAxiosError && axios.isAxiosError(error)) {
            return {
                error: true,
                message: error.message,
                status: error.response?.status,
            };
        }
        return {
            error: true,
            message: 'An unexpected error occurred',
            status: undefined,
        };
    }
}

export default axiosClient;

