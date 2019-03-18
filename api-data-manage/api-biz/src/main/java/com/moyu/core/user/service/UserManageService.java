package com.moyu.core.user.service;

import com.moyu.core.user.domain.MyUser;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 15:14
 * @Description:
 */
public interface UserManageService {
    /**
     * 查询用户数据
     *
     * @param name
     * @param page
     * @param size
     * @return
     */
    List<MyUser> findAll(String name, int page, int size);

    /**
     * 保存用户数据 ， 并且要验证是否重复添加
     * @param user
     */
    void saveUserManage(MyUser user);

    void upUserManage(MyUser user);

    /**
     * 删除用户数据 ，并且验证 删除几条
     * @param ids
     */
    void removeUserByIds(List<Integer> ids);

    void changeState(Integer state, Integer userId);
}
