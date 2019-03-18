package com.moyu.util;

import java.util.List;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 09:54
 * @Description:
 */
public class ListUtil {
    /**
     * List 仅取一条
     * @param list
     * @return
     */
    public static Object getOne(List list){
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
