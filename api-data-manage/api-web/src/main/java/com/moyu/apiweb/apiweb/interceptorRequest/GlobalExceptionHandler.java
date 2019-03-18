package com.moyu.apiweb.apiweb.interceptorRequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: gxj
 * @description: 统一异常拦截
 * @date: 2017/10/24 10:31
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodHandler() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", "500");
        jsonObject.put("returnMsg", "请求方式有误");
        return jsonObject;
    }



    /**
     * 权限不足报错拦截
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject unauthorizedExceptionHandler() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", "500");
        jsonObject.put("returnMsg", "权限不足");
        return jsonObject;
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public JSONObject unauthenticatedException() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", "500");
        jsonObject.put("returnMsg", "未登录");
        return jsonObject;
    }
}
