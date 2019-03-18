package com.moyu.core.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.moyu.core.user.dao.MyMenuDao;
import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.model.MyMenuModel;
import com.moyu.core.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 11:33
 * @Description:
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MyMenuDao myMenuDao;

    @Override
    public List<MyMenuModel> findAll() {
       return myMenuDao.selectAll(1);
    }

    @Override
    public List<MyMenuModel> findMenuManage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<MyMenuModel> all = myMenuDao.selectAll(2);
        return all;
    }
}
