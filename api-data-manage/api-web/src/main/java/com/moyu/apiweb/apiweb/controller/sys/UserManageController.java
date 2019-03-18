package com.moyu.apiweb.apiweb.controller.sys;

import com.github.pagehelper.PageInfo;
import com.moyu.apiweb.apiweb.util.BaseResponse;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:11
 * @Description:
 */
@RestController
public class UserManageController extends BaseResponse {

    private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

    @Autowired
    private UserManageService userManageService;
    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/userManage")
    public String userManage(String name,Integer page,Integer size){
        List<MyUser> all = userManageService.findAll(name,page,size);
        PageInfo<MyUser> pageInfo = new PageInfo<MyUser>(all);
        return successJsonResult(all,pageInfo,"查询成功");
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/userManage")
    public String userManage(@RequestParam(value = "ids") List<Integer> ids){
        userManageService.removeUserByIds(ids);
        return successJsonResult();
    }
    /**
     * 用户新增数据
     * @param user
     * @returnmenuManage
     */
    @PostMapping("/userManage")
    public String userManage(@RequestBody MyUser user){
        userManageService.saveUserManage(user);
        return successJsonResult(user.getId());
    }
    /**
     * 用户修改数据
     * @param user
     * @returnmenuManage
     */
    @PutMapping("/userManage")
    public String putUserManage(@RequestBody MyUser user){
        userManageService.upUserManage(user);
        return successJsonResult("修改成功");
    }

    /**
     * 启用或者禁用
     * @param state
     * @param userId
     * @return
     */
    @PutMapping("/changeState")
    public String changeState(Integer state,Integer userId){
        userManageService.changeState(state,userId);
        return successJsonResult("状态修改成功");
    }
}
