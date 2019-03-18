package com.moyu.apiweb.apiweb.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.apiweb.shiro.MyShiroRealm;
import com.moyu.core.user.domain.MyUser;
import com.moyu.util.StrinUtil;
import com.moyu.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 09:25
 * @Description: 登录管理
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/auth")
    public Map auth(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        logger.info("request.getPathInfo()"+request.getPathInfo());
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");

        Map map = new HashMap<String,Object>();
        if(!StrinUtil.isEmpty(userName,password)){
            map.put("code",500);
            map.put("msg","用户名密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            map.put("sessionId",subject.getSession().getId());


            String remoteIP = WebUtil.getRemoteIP(request);
            String ipAddress = WebUtil.getIpAddress(remoteIP);

            logger.info("登录IP:"+ remoteIP+" ;登录地址:"+ipAddress);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            map.put("code",500);
            map.put("msg","password error!");
            return map;
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            map.put("code",500);
            map.put("msg","username error!");
            return map;
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            map.put("code",500);
            map.put("msg","times error");
            return map;
        }
        map.put("code",200);
        map.put("msg","认证成功");
        return map;
    }

    @GetMapping("/logout")
    public Map logout() {
        Map map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        map.put("code",200);
        map.put("msg","退出登陆");
        return map;
    }
    @GetMapping("/403")
    public Map un403() {
        Map map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        map.put("code",200);
        map.put("msg","未授权");
        return map;
    }

}
