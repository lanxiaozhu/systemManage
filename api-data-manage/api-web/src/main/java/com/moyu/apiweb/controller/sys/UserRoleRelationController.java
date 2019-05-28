package com.moyu.apiweb.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.util.BaseResponse;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.service.UserRoleRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:11
 * @Description: 用户于角色关闭关系的查询
 */
@RestController
public class UserRoleRelationController extends BaseResponse {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleRelationController.class);


    @Autowired
    private UserRoleRelationService userRoleRelationService;

    /**
     * 查询用户和角色关系
     * @param userId
     * @return
     */
    @GetMapping("/userRoleRelation")
    public String userRoleRelation(Integer userId){
        List<MyRole> seletedRolle = userRoleRelationService.selectdRole(userId);
        return successJsonResult(seletedRolle);
    }

    /**
     * 保存用户和角色关系
     * @param param
     * @return
     */
    @PostMapping("/userRoleRelation")
    public Map userRoleRelation(@RequestBody JSONObject param){
        Map<String,Object> data = new HashMap();
        JSONArray ids = param.getJSONArray("ids");
        Integer userId = param.getInteger("userId");
        userRoleRelationService.saveUserRoleRelation(ids,userId);

        data.put("code",200);
        data.put("data",null);
        data.put("msg","成功");
        return data;
    }
    /**
     * 修改用户和角色关系
     * @param param
     * @return
     */
    @PutMapping("/userRoleRelation")
    public String putRoleRelation(@RequestBody JSONObject param){
        JSONArray ids = param.getJSONArray("ids");
        Integer userId = param.getInteger("userId");
        userRoleRelationService.putRoleRelation(ids,userId);


        return successJsonResult();
    }
}
