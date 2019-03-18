package com.moyu.core.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.moyu.core.user.dao.MyUserDao;
import com.moyu.core.user.dao.mapper.MyUserMapper;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.domain.MyUserExample;
import com.moyu.core.user.service.UserManageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:14
 * @Description:
 */
@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private MyUserMapper myUserMapper;

    @Resource
    private MyUserDao myUserDao;

    @Override
    public List<MyUser> findAll(String name, int page, int size) {
        MyUserExample example = new MyUserExample();
        if (StringUtils.isNotEmpty(name)) {
            example.createCriteria().andRealNameEqualTo(name);
        }
        example.setOrderByClause("id desc");

        PageHelper.startPage(page, size);
        List<MyUser> myUsers = myUserMapper.selectByExample(example);

        return myUsers;
    }

    @Override
    public void saveUserManage(MyUser user) {
        myUserMapper.insertSelective(user);
    }

    @Override
    public void upUserManage(MyUser user) {
        MyUserExample example = new MyUserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        myUserMapper.updateByExampleSelective(user,example);
    }

    @Override
    public void removeUserByIds(List<Integer> ids) {
        myUserDao.deleteUserByIds(ids);
    }

    @Override
    public void changeState(Integer state, Integer userId) {

        MyUserExample example = new MyUserExample();
        example.createCriteria().andIdEqualTo(userId);

        MyUser record = new MyUser();record.setState(state);
        myUserMapper.updateByExampleSelective(record,example);
    }
}
