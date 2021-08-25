import axios from "axios";
import cookies from 'react-cookies'

export let endpoints = {
    // Chỗ này PHẢI có dấu / ở cuối
    'categories': '/categories/',
    'courses': '/courses/',
    'users': '/users/',
    'current-user': '/users/current-user/',
    'login': '/o/token/'
}

export let AuthAPI = axios.create({
    baseURL: 'http://127.0.0.1:8000/',
    headers: {
        'Authorization': `Bearer ${cookies.load('access_token')}`
    }
})

export default axios.create({
    baseURL: 'http://127.0.0.1:8000/',
})