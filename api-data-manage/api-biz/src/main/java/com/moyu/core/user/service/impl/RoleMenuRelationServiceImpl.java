package com.moyu.core.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.moyu.core.user.dao.MyRoleDao;
import com.moyu.core.user.dao.mapper.MyRoleMapper;
import com.moyu.core.user.dao.mapper.MyRoleMenuRelationMapper;
import com.moyu.core.user.domain.*;
import com.moyu.core.user.service.RoleMenuRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: wishm
 * @Date: 2019/3/18 11:04
 * @Description:
 */
@Service
public class RoleMenuRelationServiceImpl implements RoleMenuRelationService {

    @Resource
    private MyRoleDao myRoleDao;
    @Resource
    private MyRoleMenuRelationMapper myRoleMenuRelationMapper;

    @Override
    public List<MyMenu> selectdMenu(Integer roleId) {
        if(Objects.nonNull(roleId)){
            return myRoleDao.selectdMenu(roleId);
        }
        return null;
    }

    @Override
    public void saveRoleMenuRelation(JSONArray ids, Integer roleId) {
        //1、删除历史关系
        MyRoleMenuRelationExample example = new MyRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        myRoleMenuRelationMapper.deleteByExample(example);
        //2、保存修改后新关系组
        saveRoleMenuRelation(roleId,ids);
    }

    private void saveRoleMenuRelation(Integer roleId,JSONArray ids){
        MyRoleMenuRelation record = new MyRoleMenuRelation();
        record.setRoleId(roleId);
        for (int i = 0; i <ids.size() ; i++) {
            Integer id =(Integer)ids.get(i);
            record.setMenuId(id);
            myRoleMenuRelationMapper.insertSelective(record);
        }
    }
}
