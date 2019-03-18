package com.moyu.apiweb.apiweb.util;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/28 22:26
 * @Description:
 */
public enum CodeEnum {

    SUCCESS("success", 200),
    FAIL("fail", 500);

    private String msg;
    private int stateCode;

    CodeEnum(String msg, int stateCode) {
        this.msg = msg;
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }
}
