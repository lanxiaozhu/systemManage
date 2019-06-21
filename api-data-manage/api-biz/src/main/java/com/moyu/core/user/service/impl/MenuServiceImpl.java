package com.moyu.core.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.moyu.core.user.dao.MyMenuDao;
import com.moyu.core.user.dao.mapper.MyMenuMapper;
import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.domain.MyMenuExample;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.model.MyMenuModel;
import com.moyu.core.user.service.MenuService;
import com.moyu.core.user.service.RoleMenuRelationService;
import com.moyu.core.user.service.UserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:33
 * @Description:
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MyMenuDao myMenuDao;

    @Resource
    private MyMenuMapper myMenuMapper;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    @Override
    public List<MyMenuModel> findAll(Integer userId, String superAdmin, MyUser userInfo) {
        Set<Integer> menuIdOne = new HashSet<>();
        Set<Integer> menuIdTwo = new HashSet<>();


        if (superAdmin.equals(userInfo.getLoginName())) {
            List<MyMenuModel> models = myMenuDao.selectAll(1);
            return models;
        }
        //显示的设置权限和角色，避免下次再去数据库获取，提高效率
        List<MyRole> myRoles = userRoleRelationService.selectdRole(null, userId);
        for (MyRole role : myRoles) {

            List<MyMenu> myMenus = roleMenuRelationService.selectdMenu(role.getId());
            myMenus.stream().forEach(item ->
            {
                if (item.getMenuLever() == 1) {
                    menuIdOne.add(item.getId());
                }
                if (item.getMenuLever() == 2) {
                    menuIdTwo.add(item.getId());
                }
            });
        }
        if (!menuIdOne.isEmpty()) {
            List<MyMenuModel> models = myMenuDao.selectAlls(menuIdOne);

            for (MyMenuModel model : models) {
                List<MyMenu> myMenu = menuIdTwo.isEmpty() ? null : myMenuDao.findMenuByParentIds(model.getId(), menuIdTwo);
                model.setMenuList(myMenu);
            }
            return models;
        }
        return null;
    }

    @Override
    public List<MyMenuModel> findMenuManage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MyMenuModel> all = myMenuDao.selectAll(2);
        return all;
    }

    @Override
    public void saveMune(MyMenu myMenu) {
        myMenuMapper.insertSelective(myMenu);
    }

    @Override
    public void updateMenu(MyMenu myMenu) {
        MyMenuExample example = new MyMenuExample();
        example.createCriteria().andIdEqualTo(myMenu.getId());

        myMenuMapper.updateByExampleSelective(myMenu,example);
    }


}
