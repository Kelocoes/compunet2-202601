import axiosClient, { safeRequest } from '../../lib/axios/axiosClient';

export function getAll() {
    const request = axiosClient.get('/rest/games');
    return safeRequest(request);
}

const gamesService = {
    getAll,
};

export default gamesService;