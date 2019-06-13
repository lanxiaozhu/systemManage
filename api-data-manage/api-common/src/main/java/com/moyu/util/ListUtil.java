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
     *
     * @param list
     * @return
     */
    public static <T> T getOne(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }
}
