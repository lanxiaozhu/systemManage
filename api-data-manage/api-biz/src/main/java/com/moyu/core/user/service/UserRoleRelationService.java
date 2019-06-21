package com.moyu.core.user.service;

import com.alibaba.fastjson.JSONArray;
import com.moyu.core.user.domain.MyRole;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/21 17:24
 * @Description: 用户和角色关系业务
 */
public interface UserRoleRelationService {

    Integer saveUserRoleRelation(JSONArray ids, Integer userId);

    List<MyRole> selectdRole(String dbName, Integer userId);

    void putRoleRelation(JSONArray ids, Integer userId);
}
