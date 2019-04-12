import axios from "axios";
import router from '@/router'
import {MessageBox,Notification} from 'element-ui' /*引入elementUi */// 2、所有的页面路由到此

/* 创建实例、 */
const service = axios.create({
   // baseURL: 
   //timeout: 3000
});

/****** request拦截器==>对请求参数做处理 ******/
service.interceptors.request.use(config => {

    config.withCredentials = true // 允许携带token ,这个是解决跨域产生的相关问题
      let token = localStorage.getItem('Authorization')
      if (token) {
        config.headers = {
          'Authorization': token,
        }
      }
    return config;
}, error => {  //请求错误处理
    Promise.reject(error)
});

/****** respone拦截器==>对响应做处理 ******/
service.interceptors.response.use(
    response => {  //成功请求到数据
       if(response.data.code == '401'){
        MessageBox.alert('登陆信息超时，请重新登陆','登陆超时',{
            confirmButtonText: '跳转登陆页',
            callback: action =>{
                window.location.href = '/'
            }
        })
       }
       if(response.data.code == '999'){
        Notification.error({
            title: '抱歉',
            message: '您没有权限进入，请联系管理员',
            duration: 3000
          });
        router.replace({
            path: '/noPermission',
        })
    }
        return Promise.resolve(response)

    },
    error => {  //响应错误处理
        console.log(JSON.stringify(error));
 
        let text = JSON.parse(JSON.stringify(error)).response.status === 404
            ? '404'
            : '网络异常，请重试';
        return Promise.reject(error)
    }
);
export default service;
