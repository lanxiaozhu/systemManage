package com.moyu.crawler.beike.dao.mapper;

import com.moyu.crawler.beike.domian.MoyuHouseInfo;
import com.moyu.crawler.beike.domian.MoyuHouseInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("moyuHouseInfoMapper")
public interface MoyuHouseInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    long countByExample(MoyuHouseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int deleteByExample(MoyuHouseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int insert(MoyuHouseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int insertSelective(MoyuHouseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    List<MoyuHouseInfo> selectByExample(MoyuHouseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    MoyuHouseInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") MoyuHouseInfo record, @Param("example") MoyuHouseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByExample(@Param("record") MoyuHouseInfo record, @Param("example") MoyuHouseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByPrimaryKeySelective(MoyuHouseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table moyu_house_info
     *
     * @mbg.generated Wed May 08 17:11:58 CST 2019
     */
    int updateByPrimaryKey(MoyuHouseInfo record);
}