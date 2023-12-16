// router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Login from './components/LoginForm'
import Register from './components/RegisterForm'
import MyBooks from './components/user/MyBooks'
import BrowseBooks from './components/user/BrowseBooks'

const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/user/mybooks',
        name: 'mybooks',
        component: MyBooks
    },
    {
        path: '/user/browsebooks',
        name: 'browse',
        component: BrowseBooks
    }
]

const router = createRouter({ history: createWebHistory(), routes })
export default router
