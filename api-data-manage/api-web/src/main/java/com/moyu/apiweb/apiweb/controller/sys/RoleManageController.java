package com.moyu.apiweb.apiweb.controller.sys;

import com.github.pagehelper.PageInfo;
import com.moyu.apiweb.apiweb.util.BaseResponse;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.RoleManageService;
import com.moyu.core.user.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:11
 * @Description: 角色管理
 */
@RestController
public class RoleManageController extends BaseResponse {

    private static final Logger logger = LoggerFactory.getLogger(RoleManageController.class);

    @Autowired
    private RoleManageService roleManageService;
    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/roleManage")
    public Map roleManage(Integer page,Integer size){
        Map<String,Object> data = new HashMap();

        List<MyRole> all = roleManageService.findAll(page,size);
        PageInfo<MyRole> pageInfo = new PageInfo<MyRole>(all);
        data.put("code",200);
        data.put("data",all);
        data.put("page", pageInfo);
        data.put("msg","成功");
        return data;
    }
    /**
     * 获取角色信息
     * @return
     */
    @GetMapping("/roleManageAll")
    public String roleManage(){
        List<MyRole> all = roleManageService.findAll(null,null);
        return  successJsonResult(all);
    }
    @PostMapping("/roleManage")
    public Map roleManage(@RequestBody MyRole role){
        Map<String,Object> data = new HashMap();
        roleManageService.saveRoleManage(role);
        data.put("code",200);
        data.put("data",null);
        data.put("msg","成功");
        return data;
    }
}
