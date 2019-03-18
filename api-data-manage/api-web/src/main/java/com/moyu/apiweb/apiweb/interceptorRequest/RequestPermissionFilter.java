package com.moyu.apiweb.apiweb.interceptorRequest;

import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.apiweb.controller.sys.UserManageController;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:11
 * @Description: 登陆已经认证的关键过滤器
 */
public class RequestPermissionFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestPermissionFilter.class);

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (this.isLoginRequest(request, response)){
            return this.executeLogin(request, response);
        }
        //返回false表示该拦截器实例已经处理了，将直接返回即可
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 拦截未登录的请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("拦截未登录的请求-->");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "401");
        jsonObject.put("msg", "登陆已过期,请重新登陆");
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(RequestPermissionFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
