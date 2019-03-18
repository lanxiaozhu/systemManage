package com.moyu.core.user.dao;

import com.moyu.core.user.model.MyMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:48
 * @Description:
 */
public interface MyMenuDao {
    List<MyMenuModel> selectAll(@Param("ids") int i);
}
