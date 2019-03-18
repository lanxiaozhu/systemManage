import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/sys/login'
import intro from '@/components/page/intro/intro'
import mainPage from '@/components/sys/mainPage'

import productBring from '@/components/page/product/productBring'
import productRead from '@/components/page/product/productRead'
import productReserve from '@/components/page/product/productReserve'

import menuManage from '@/components/page/systemManage/menuManage'
import roleManage from '@/components/page/systemManage/roleManage'
import sysParam from '@/components/page/systemManage/sysParam'
import userManage from '@/components/page/systemManage/userManage'


Vue.use(Router)

const router =   new Router({
   // mode:'history',//去除 地址栏 /#
  routes: [
      {
        path: '/',
        name: 'Login',
        component: Login,
      },
      {
        path: '/mainPage',
        name: 'mainPage',
        component: mainPage,
      children: [  //这里就是二级路由的配置
        {
          path: '/intro',
          name: '介绍',
          component: intro,
        },
        {
          path: '/productBring',
          name: 'productBring',
          component: productBring
        },
        {
          path: '/productRead',
          name: 'productRead',
          component: productRead
        },
        {
          path: '/productReserve',
          name: 'productReserve',
          component: productReserve
        },
        {
          path: '/menuManage',
          name: 'menuManage',
          component: menuManage
        },
        {
          path: '/roleManage',
          name: 'roleManage',
          component: roleManage
        },
        {
          path: '/sysParam',
          name: 'sysParam',
          component: sysParam
        },
        {
          path: '/userManage',
          name: 'userManage',
          component: userManage
        }
      ]
   },
    
  ]
})
//3
// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  console.log('to.path'+to.path)
  if (to.path === '/') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');
    if (token === 'null' || token === '') {//token失效后 跳转
      next('/');
    } else {
      next();
   }
  }
});
 
export default router;