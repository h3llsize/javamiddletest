import Vue from 'vue';
import store from '@/store';
import VueRouter from 'vue-router';
import AuthPage from '@/components/AuthPage.vue';
import MainPage from '@/components/MainPage.vue';
import AdminPage from '@/components/AdminPage.vue';
import YourIRPage from '@/components/YourIRPage.vue';
import NotFoundPage from '@/components/NotFoundPage.vue';

Vue.use(VueRouter);

const routes = [
  {
    name: 'auth', component: AuthPage, path: '/auth', meta: { requiresNotAuth: true }
  },
  {
    name: 'main', component: MainPage, path: '/', meta: { requiresAuth: true }
  },
  {
    name: 'admin', component: AdminPage, path: '/admin', meta: { requiresAuth: true }
  },
  {
    name: 'ir', component: YourIRPage, path: '/your-ir', meta: { requiresAuth: true }
  },
  {
    name: 'error', component: NotFoundPage, path: '*', meta: { requiresNotAuth: true }
  },
  {
    path: '/main', redirect: { name: 'main' }, meta: { requiresAuth: true }
  },
  {
    path: '/ir', redirect: { name: 'ir' }, meta: { requiresAuth: true }
  },
];

const router = new VueRouter({
  routes,
});

router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)) {
    if (store.state.authorized) {
      next();
      return;
    }
    next({ name: 'auth' });
  } else {
    next();
  }
})

export default router;
