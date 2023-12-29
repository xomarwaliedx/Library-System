// router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Login from './components/LoginForm'
import Register from './components/RegisterForm'
import MyBooks from './components/user/MyBooks'
import BrowseBooks from './components/user/BrowseBooks'
import RegisterByAdmin from './components/admin/RegisterUser'
import AddBook from './components/admin/AddBook'
import BorrowedBooks from './components/admin/BorrowedBooks'
import ViewAllBooks from './components/admin/ViewAllBooks'
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
        name: 'browsebooks',
        component: BrowseBooks
    }
    ,
    {
        path: '/admin/reguser',
        name: '/admin/reguser',
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
    },
    {
        path: '/admin/viewallbooks',
        name: 'viewallbooks',
        component: ViewAllBooks
    }
    
]

const router = createRouter({ history: createWebHistory(), routes })

router.beforeEach(async (to, from, next) => {
    const isAuthenticated = await new Promise((resolve) => {
        const userId = localStorage.getItem('userId');
        console.error(localStorage.getItem('userId'))
        resolve(!!userId); // Resolve with a boolean indicating whether userId exists
    });

    if (isAuthenticated==null && (to.name !== 'login' && to.name !== 'register'  && to.name !== 'aboutlibrary')) {
        next('/login');
    } else {
        next();
    }
});



export default router
