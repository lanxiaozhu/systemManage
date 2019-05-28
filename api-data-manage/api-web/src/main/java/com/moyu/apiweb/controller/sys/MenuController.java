package com.moyu.apiweb.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moyu.apiweb.util.BaseResponse;
import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.model.MyMenuModel;
import com.moyu.core.user.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MenuController extends BaseResponse {
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Value("${superAdmin}")
    private String superAdmin;

    @GetMapping("/menu/qeuryAll")
    public Map qeuryAll() {
        Map map = new HashMap<String, Object>();
        map.put("code", 200);
        MyUser userInfo = getUserInfo();
        Integer userId = getUserInfo().getId();
        map.put("data", menuService.findAll(userId,superAdmin,userInfo));
        map.put("msg", "成功");
        return map;
    }

    @GetMapping("/menuManage")
    public Map menuManage(Integer page, Integer size) {
        Map data = new HashMap<String, Object>();
        List<MyMenuModel> all = menuService.findMenuManage(page, size);
        PageInfo<MyMenuModel> pageInfo = new PageInfo<MyMenuModel>(all);
        data.put("code", 200);
        data.put("data", all);
        data.put("page", pageInfo);
        data.put("msg", "成功");

        return data;
    }

    @PostMapping("/menuManage")
    public String saveMenu(@RequestBody JSONObject jsonObject) {
        Integer parentId = jsonObject.getInteger("parentId");
        JSONObject menu = jsonObject.getJSONObject("menu");

        MyMenu myMenu = new MyMenu();
        myMenu.setParentId(parentId);
        myMenu.setMenuName(menu.getString("menuName"));
        myMenu.setMenuType(menu.getString("menuType"));
        myMenu.setPermissionCode(menu.getString("permissionCode"));
        myMenu.setMenuUrl(menu.getString("menuUrl"));
        myMenu.setMenuIcon(menu.getString("menuIcon"));
        myMenu.setSort(menu.getInteger("sort"));
        myMenu.setMenuLever(menu.getInteger("menuLever"));
        menuService.saveMune(myMenu);
        return successJsonResult();
    }

    @PutMapping("/menuManage")
    public String updateMenu(@RequestBody JSONObject jsonObject) {
        JSONObject menu = jsonObject.getJSONObject("menu");

        MyMenu myMenu = new MyMenu();
        myMenu.setId(menu.getInteger("id"));
        myMenu.setMenuName(menu.getString("menuName"));
        myMenu.setMenuType(menu.getString("menuType"));
        myMenu.setPermissionCode(menu.getString("permissionCode"));
        myMenu.setMenuUrl(menu.getString("menuUrl"));
        myMenu.setMenuIcon(menu.getString("menuIcon"));
        myMenu.setSort(menu.getInteger("sort"));
        myMenu.setMenuLever(menu.getInteger("menuLever"));
        menuService.updateMenu(myMenu);
        return successJsonResult();
    }

}
