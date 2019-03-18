package com.moyu.core.user.dao;

import com.moyu.core.user.domain.MyMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: wishm
 * @Date: 2019/3/18 11:08
 * @Description:
 */
public interface MyRoleDao {

     List<MyMenu> selectdMenu(@Param("roleId") Integer roleId);
}
