package com.moyu.core.user.service;

import com.moyu.core.user.model.MyMenuModel;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:33
 * @Description:
 */
public interface MenuService {
    //查询全部菜单
    List<MyMenuModel> findAll();

    List<MyMenuModel> findMenuManage(Integer page, Integer size);
}
