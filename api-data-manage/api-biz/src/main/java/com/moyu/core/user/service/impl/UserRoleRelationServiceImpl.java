package com.moyu.core.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.moyu.core.user.dao.MyUserDao;
import com.moyu.core.user.dao.mapper.MyRoleMapper;
import com.moyu.core.user.dao.mapper.MyUserRoleRelationMapper;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUserRoleRelation;
import com.moyu.core.user.domain.MyUserRoleRelationExample;
import com.moyu.core.user.service.UserRoleRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/21 17:24
 * @Description:
 */
@Service("userRoleRelationService")
public class UserRoleRelationServiceImpl implements UserRoleRelationService {

    @Resource
    private MyUserRoleRelationMapper myUserRoleRelationMapper;

    @Resource
    private MyUserDao myUserDao;



    @Override
    public Integer saveUserRoleRelation(JSONArray ids, Integer userId) {
        for (Object id : ids) {
            MyUserRoleRelation myUserRoleRelation = new MyUserRoleRelation();
            myUserRoleRelation.setUserId(userId);
            if (!StringUtils.isNumeric(id.toString())) {
                break;
            }
            myUserRoleRelation.setRoleId(Integer.parseInt(id.toString()));
            myUserRoleRelationMapper.insertSelective(myUserRoleRelation);
        }
        return null;
    }

    @Override
    public List<MyRole> selectdRole(Integer userId) {
        if(Objects.nonNull(userId)){
            return myUserDao.selectdRole(userId);
        }
        return null;
    }

    @Override
    public void putRoleRelation(JSONArray ids, Integer userId) {
        //1、强行删除
        myUserDao.deleteRoleRelationByUserId(userId);
        //2 直接新增
        saveUserRoleRelation(ids,userId);
    }
}
