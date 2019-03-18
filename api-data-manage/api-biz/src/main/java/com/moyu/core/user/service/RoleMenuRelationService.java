package com.moyu.core.user.service;

import com.alibaba.fastjson.JSONArray;
import com.moyu.core.user.domain.MyMenu;

import java.util.List;

/**
 * @Auther: wishm
 * @Date: 2019/3/18 11:04
 * @Description:
 */
public interface RoleMenuRelationService {
    List<MyMenu> selectdMenu(Integer roleId);

    void saveRoleMenuRelation(JSONArray ids, Integer roleId);
}
