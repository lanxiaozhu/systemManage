package com.moyu.apiweb.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.util.BaseResponse;
import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.service.RoleMenuRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:11
 * @Description: 菜单于角色关系的查询
 */
@RestController
public class RoleMenuRelationController extends BaseResponse {

    private static final Logger logger = LoggerFactory.getLogger(RoleMenuRelationController.class);


    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    /**
     * 查询菜单于角色关系
     * @param roleId
     * @return
     */
    @GetMapping("/roleMenuRelation")
    public String roleMenuRelation(Integer roleId){
        List<MyMenu> seletedMenu = roleMenuRelationService.selectdMenu(roleId);
        return successJsonResult(seletedMenu);
    }

    /**
     * 保存用户和角色关系
     * @param param
     * @return
     */
    @PostMapping("/roleMenuRelation")
    public String roleMenuRelation(@RequestBody JSONObject param){
        JSONArray ids = param.getJSONArray("munes");
        Integer roleId = param.getInteger("roleId");
        roleMenuRelationService.saveRoleMenuRelation(ids,roleId);
        return successJsonResult();
    }
    /**
     * 修改用户和角色关系
     * @param param
     * @return
     */
//    @PutMapping("/userRoleRelation")
//    public String putRoleRelation(@RequestBody JSONObject param){
//        Map<String,Object> data = new HashMap();
//        JSONArray ids = param.getJSONArray("ids");
//        Integer userId = param.getInteger("userId");
//        userRoleRelationService.putRoleRelation(ids,userId);
//
//
//        return successJsonResult();
//    }
}
