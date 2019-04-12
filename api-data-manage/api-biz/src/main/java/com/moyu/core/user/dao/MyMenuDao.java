package com.moyu.core.user.dao;

import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.model.MyMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:48
 * @Description:
 */
public interface MyMenuDao {
    List<MyMenuModel> selectAll(@Param("ids") int i);

    List<MyMenuModel> selectAlls(@Param("ids") Set<Integer> menuIdOne);

    List<MyMenu> findMenuByParentIds(@Param("id")Integer id, @Param("ids")Set<Integer> menuIdTwo);
}
