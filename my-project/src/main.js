// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui' /*引入elementUi */// 2、所有的页面路由到此
import 'element-ui/lib/theme-chalk/index.css' /*引入elementUi */
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from '@/components/store'
import moment from 'moment' //日期格式化 



Vue.use(ElementUI) /*引入elementUi */
Vue.use(VueAxios,axios);
axios.defaults.baseURL = "http://127.0.0.1:8088/" ; // 关键步骤–填写后台请求统一的地址


Vue.config.productionTip = false

Vue.filter('dateformat', function(dataStr, pattern = 'YYYY-MM-DD HH:mm:ss') {
  return moment(dataStr).format(pattern)
})
  
  
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

