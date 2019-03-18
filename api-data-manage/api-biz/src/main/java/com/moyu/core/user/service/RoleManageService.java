package com.moyu.core.user.service;

import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUser;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/20 14:37
 * @Description:
 */
public interface RoleManageService {
    List<MyRole> findAll(Integer page, Integer size);

    void saveRoleManage(MyRole role);
}
