package com.moyu.core.mongoUtil;

/**
 * @Auther: wishm
 * @Date: 2019/6/11 23:29
 * @Description:
 */
public class TestUser {
    private int id;
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
