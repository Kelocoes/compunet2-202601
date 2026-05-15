import axiosClient, { safeRequest } from '../../lib/axios/axiosClient';


export function login(credentials) {
    const request = axiosClient.post('/rest/public/auth/login', credentials);
    return safeRequest(request);
}

const authService = {
    login,
};

export default authService;

