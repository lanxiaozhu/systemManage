package com.moyu.core.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.moyu.core.user.constant.RoleConstant;
import com.moyu.core.user.dao.mapper.MyRoleMapper;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyRoleExample;
import com.moyu.core.user.service.RoleManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/20 14:37
 * @Description:
 */
@Service("roleManageService")
public class RoleManageServiceImpl implements RoleManageService {

    @Resource
    private MyRoleMapper myRoleMapper;

    @Override
    public List<MyRole> findAll(Integer page, Integer size) {
        if (Objects.nonNull(page) || Objects.nonNull(size)) {
            PageHelper.startPage(page, size);
        }
        MyRoleExample example = new MyRoleExample();
        example.setOrderByClause("id desc");
        List<MyRole> myRoles = myRoleMapper.selectByExample(example);
        return myRoles;
    }

    @Override
    public void saveRoleManage(MyRole role) {
        role.setIsDel(RoleConstant.IS_DEL);
        myRoleMapper.insertSelective(role);
    }
}
