package com.moyu.apiweb;

/**
 * @Auther: wishm
 * @Date: 2019/4/12 19:47
 * @Description:
 */
public class HashCodeHelper {

    public int id;
    public String name;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        //如果是同一个对象 返回true
        if (this == obj) {
            return true;
        }
        //判断是否类型相同
        if (this.getClass() != obj.getClass()) {
            return true;
        }


        //若类型相同 可以强制转换
        HashCodeHelper h = (HashCodeHelper) obj;
        return id == h.id && name.equals(h.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;//用31的好处：VM会自动把它转化成位运算（cpu支持的运算），“31*num=(31<<5)-num”。这样就很快了
        int result = 23;//这个23是写在这个位置的一种习惯数字
        result = prime * result + id;//二个属性的“加盟”顺序随便，推荐由上至下依次加入
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Hash{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
