package com.moyu.core.user.model;

import com.moyu.core.user.domain.MyMenu;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:51
 * @Description:
 */
public class MyMenuModel extends MyMenu {

    private List<MyMenu> menuList;

    public List<MyMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MyMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "MyMenuModel{" +
                "menuList=" + menuList +
                '}';
    }
}
