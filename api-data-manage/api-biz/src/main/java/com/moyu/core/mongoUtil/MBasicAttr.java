package com.moyu.core.mongoUtil;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: wishm
 * @Date: 2019/6/11 22:58
 * @Description: mongo 基础超类模板
 */
public class MBasicAttr<T> implements Serializable {

    private static final long serialVersionUID = 354791307338870065L;
    /**
     * 唯一编号(mongoDB where ID)
     * 有规则 或者 无规则 必须保证 唯一
     * tquserid_tqorder_no_merid_appid_meruserid_merorderno
     */
    private String uniqueId;
    /**
     * 原始数据
     */
    private Object sourceData;
    /**
     * 基础信息结构对象
     */
    private T basic;
    /**
     * 添加时间
     */
    private Date addTime = getNow();

    /*public static <T> MBasicAttr getInstance(T basic){
        return new MBasicAttr().rsThis(basic);
    }*/
    public MBasicAttr rsThis(T basic) {
        this.basic = basic;
        return this;
    }


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Object getSourceData() {
        return sourceData;
    }

    public void setSourceData(Object sourceData) {
        this.sourceData = sourceData;
    }

    public T getBasic() {
        return basic;
    }

    public void setBasic(T basic) {
        this.basic = basic;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

}
