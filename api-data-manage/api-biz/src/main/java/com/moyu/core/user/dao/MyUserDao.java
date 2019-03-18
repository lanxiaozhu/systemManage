package com.moyu.core.user.dao;

import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUserRoleRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:48
 * @Description:
 */
public interface MyUserDao {

    void deleteUserByIds(@Param("ids") List<Integer> ids);

    List<MyRole> selectdRole(@Param("userId") Integer userId);

    void deleteRoleRelationByUserId(@Param("userId") Integer userId);
}
