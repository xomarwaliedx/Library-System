// router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Login from './components/LoginForm'
import Register from './components/RegisterForm'
import MyBooks from './components/user/MyBooks'
import BrowseBooks from './components/user/BrowseBooks'
import RegisterByAdmin from './components/admin/RegisterUser'
import AddBook from './components/admin/AddBook'
import BorrowedBooks from './components/admin/BorrowedBooks'
import AdminHomePage from './components/admin/AdminHomePage'
import AboutLibrary from './components/AboutLibrary'


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
        path: '/aboutlibrary',
        name: 'aboutlibrary',
        component: AboutLibrary
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
    ,
    {
        path: '/admin/registeruser',
        name: '/admin/registeruser',
        component: RegisterByAdmin
    }
    ,
    {
        path: '/admin/addbook',
        name: 'addbook',
        component: AddBook
    }
    ,
    {
        path: '/admin/borrowedbooks',
        name: 'borrowedbooks',
        component: BorrowedBooks
    },
    {
        path: '/admin',
        name: 'admin',
        component: AdminHomePage
    }
    
]

const router = createRouter({ history: createWebHistory(), routes })
export default router
