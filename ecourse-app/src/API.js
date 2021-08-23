import axios from "axios";

export let endpoints = {
    // Chỗ này PHẢI có dấu / ở cuối
    'categories': '/categories/',
    'courses': '/courses/',
    'users': '/users/'
}

export default axios.create({
    baseURL: 'http://127.0.0.1:8000/',
})