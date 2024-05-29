import axios from 'axios';

const USER_API_BASE_URL = 'http://127.0.0.1:7777/login';

class UserService{
    getUser(){
        return axios.get(USER_API_BASE_URL);
    }
}

export default new UserService()