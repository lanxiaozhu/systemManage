package com.moyu.apiweb.apiweb.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/28 22:23
 * @Description: 公共响应结果类
 */
public class BaseResponse {

    public String successJsonResult(Object data,Object page,String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data",data);
        result.put("page",page);
        result.put("code", CodeEnum.SUCCESS.getStateCode());
        result.put("msg", StringUtils.isEmpty(msg) ? CodeEnum.SUCCESS.getMsg() : msg);
        return JSON.toJSONString(result);
    }
    public String successJsonResult(Object data,Object page) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data",data);
        result.put("page",page);
        result.put("code", CodeEnum.SUCCESS.getStateCode());
        result.put("msg", CodeEnum.SUCCESS.getMsg());
        return JSON.toJSONString(result);
    }

    /**
     * 成功状态
     * @param data 数据
     * @return
     */
    public String successJsonResult(Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data",data);
        result.put("code", CodeEnum.SUCCESS.getStateCode());
        result.put("msg", CodeEnum.SUCCESS.getMsg());
        return JSON.toJSONString(result);
    }
    public String successJsonResult() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data",null);
        result.put("code", CodeEnum.SUCCESS.getStateCode());
        result.put("msg", CodeEnum.SUCCESS.getMsg());
        return JSON.toJSONString(result);
    }
}
