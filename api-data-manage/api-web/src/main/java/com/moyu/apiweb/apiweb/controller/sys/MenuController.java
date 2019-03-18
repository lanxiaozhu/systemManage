package com.moyu.apiweb.apiweb.controller.sys;

import com.github.pagehelper.PageInfo;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.model.MyMenuModel;
import com.moyu.core.user.service.MenuService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 09:25
 * @Description: 菜单管理
 */
@RestController
public class MenuController {
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequiresPermissions("user:add")
    @GetMapping("/menu/qeuryAll")
    public Map qeuryAll() {
        Map map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("data", menuService.findAll());
        map.put("msg", "成功");
        return map;
    }

    @GetMapping("/menuManage")
    public Map menuManage(Integer page,Integer size){
        Map data = new HashMap<String, Object>();
        List<MyMenuModel> all = menuService.findMenuManage(page,size);
        PageInfo<MyMenuModel> pageInfo = new PageInfo<MyMenuModel>(all);
        data.put("code",200);
        data.put("data",all);
        data.put("page", pageInfo);
        data.put("msg","成功");

        return data;
    }

}
