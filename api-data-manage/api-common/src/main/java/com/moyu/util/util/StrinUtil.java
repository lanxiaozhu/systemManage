package com.moyu.util.util;

import org.apache.commons.lang.StringUtils;


/**
 * @Auther: guoxianjun
 * @Date: 2019/1/25 10:11
 * @Description:
 */
public class StrinUtil {
    /**
     * 判断字符串是否为空
     * @param param
     * @return
     */
    public static  boolean isEmpty(String... param){
        boolean notNull =true;
        for (String str: param) {
            if( StringUtils.isEmpty(str)){
                notNull = false;
                return notNull;
            }
        }
        return notNull;
    }

}
