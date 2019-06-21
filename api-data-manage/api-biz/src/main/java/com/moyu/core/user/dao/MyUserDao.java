package com.moyu.core.user.dao;

import com.moyu.core.user.domain.MyRole;
import com.moyu.util.MultipleData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:48
 * @Description:
 */
public interface MyUserDao {

    void deleteUserByIds(@Param("ids") List<Integer> ids);

    @MultipleData
    List<MyRole> selectdRole(@Param("dbName")String dbName, @Param("userId") Integer userId);

    void deleteRoleRelationByUserId(@Param("userId") Integer userId);
}
