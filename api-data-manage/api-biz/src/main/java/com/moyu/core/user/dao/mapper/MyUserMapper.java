package com.moyu.core.user.dao.mapper;

import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.domain.MyUserExample;
import java.util.List;

import com.moyu.util.MultipleData;
import org.apache.ibatis.annotations.Param;

public interface MyUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    long countByExample(MyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int deleteByExample(MyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int insert(MyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int insertSelective(MyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     * gxj: dynamic_db2 default is dataSouce master
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    List<MyUser> selectByExample(MyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    MyUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") MyUser record, @Param("example") MyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByExample(@Param("record") MyUser record, @Param("example") MyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByPrimaryKeySelective(MyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_user
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByPrimaryKey(MyUser record);
}