package com.moyu.apiweb.controller.sys;

import com.moyu.core.user.dao.mapper.MoyuHouseInfoMapper;
import com.moyu.core.user.domain.MoyuHouseInfo;
import com.moyu.core.user.domain.MoyuHouseInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: wishm
 * @Date: 2019/5/13 15:44
 * @Description:
 */
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private MoyuHouseInfoMapper moyuHouseInfoMapper;

    @GetMapping("/info")
    public List<MoyuHouseInfo> getHouseInfo(){
        MoyuHouseInfoExample example = new MoyuHouseInfoExample();
        example.createCriteria().andIsDelNotEqualTo(1);
        return moyuHouseInfoMapper.selectByExample(example);
    }
}
