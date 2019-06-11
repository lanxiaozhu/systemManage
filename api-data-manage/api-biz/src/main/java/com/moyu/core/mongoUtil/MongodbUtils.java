package com.moyu.core.mongoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/3 14:55
 * @Description: mongodb  工具类
 */
@Component
public class MongodbUtils {
    /**
     * 当前对象 静态引用
     */
    public static MongodbUtils mongodbUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        mongodbUtils = this;
        mongodbUtils.mongoTemplate = this.mongoTemplate;
    }



    /**
     * 保存数据对象，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj 数据对象
     */
    public static void save(Object obj) {
        mongodbUtils.mongoTemplate.save(obj);
    }

    /**
     * 指定集合保存数据对象
     *
     * @param obj            数据对象
     * @param collectionName 集合名
     */
    public static void save(Object obj, String collectionName) {
        mongodbUtils.mongoTemplate.save(obj, collectionName);
    }

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj 数据对象
     */
    public static void remove(Object obj) {
        mongodbUtils.mongoTemplate.remove(obj);
    }

    /**
     * 指定集合 根据数据对象中的id删除数据
     *
     * @param obj            数据对象
     * @param collectionName 集合名
     */
    public static void remove(Object obj, String collectionName) {
        mongodbUtils.mongoTemplate.remove(obj, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param key            键
     * @param value          值
     * @param collectionName 集合名
     */
    public static void removeById(String key, Object value, String collectionName) {
        Criteria criteria = Criteria.where(key).is(value);
        criteria.and(key).is(value);
        Query query = Query.query(criteria);
        mongodbUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     *
     * @param accordingKey   修改条件 key
     * @param accordingValue 修改条件 value
     * @param updateKeys     修改内容 key数组
     * @param updateValues   修改内容 value数组
     * @param collectionName 集合名
     */
    public static void updateFirst(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongodbUtils.mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param accordingKey   修改条件 key
     * @param accordingValue 修改条件 value
     * @param updateKeys     修改内容 key数组
     * @param updateValues   修改内容 value数组
     * @param collectionName 集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongodbUtils.mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     *
     * @param tClass     数据对象
     * @param findKeys   查询条件 key
     * @param findValues 查询条件 value
     * @return
     */
    public static <T> List<T> find(Class<T> tClass, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongodbUtils.mongoTemplate.find(query, tClass);
    }

    /**
     * 指定集合 根据条件查询出所有结果集
     *
     * @param tClass
     * @param paramMap
     * @param collectionName
     * @param page
     * @param <T>
     * @return
     */
    public static <T> List<T> find(Class<T> tClass, Map<String, Object> paramMap, String collectionName, int page, String sort) {
        int size = 10;
        Query query = new Query();
        Criteria criteria = null;

        boolean first = true;
        for (Map.Entry<String, Object> entries : paramMap.entrySet()) {
            if (first) {
                criteria = Criteria.where(entries.getKey()).is(entries.getValue());
                first = false;
            } else {
                criteria.and(entries.getKey()).is(entries.getValue());
            }
        }
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        int skip = size * (page - 1);
        query.skip(skip).limit(size);
        query.with(new Sort(Direction.DESC, sort));
        return mongodbUtils.mongoTemplate.find(query, tClass, collectionName);
    }

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     *
     * @param tClass         数据对象
     * @param findKeys       查询条件 key
     * @param findValues     查询条件 value
     * @param collectionName 集合名
     * @param sort           排序字段
     * @return
     */
    public static <T> List<T> find(Class<T> tClass, String[] findKeys, Object[] findValues, String collectionName, String sort) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Direction.DESC, sort));
        return mongodbUtils.mongoTemplate.find(query, tClass, collectionName);
    }

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param tClass     数据对象
     * @param findKeys   查询条件 key
     * @param findValues 查询条件 value
     * @return
     */
    public static <T> T findOne(Class<T> tClass, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongodbUtils.mongoTemplate.findOne(query, tClass);
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param tClass         数据对象
     * @param findKeys       查询条件 key
     * @param findValues     查询条件 value
     * @param collectionName 集合名
     * @return
     */
    public static <T> T findOne(Class<T> tClass, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongodbUtils.mongoTemplate.findOne(query, tClass, collectionName);
    }
    public static <T> T findOne(Class<T> tClass, Map<String,Object> param, String collectionName) {

        Criteria criteria = null;
        int i=0;
        for (Map.Entry<String,Object>  paramMap: param.entrySet())
            if (i == 0) {
                criteria = Criteria.where(paramMap.getKey()).is(paramMap.getValue());
                i++;
            } else {
                criteria.and(paramMap.getKey()).is(paramMap.getValue());
            }
        Query query = Query.query(criteria);
        return mongodbUtils.mongoTemplate.findOne(query, tClass, collectionName);
    }
    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param tClass 数据对象
     * @return
     */
    public static <T> List<T> findAll(Class<T> tClass) {
        return mongodbUtils.mongoTemplate.findAll(tClass);
    }

    /**
     * 指定集合 查询出所有结果集
     *
     * @param tClass         数据对象
     * @param collectionName 集合名
     * @return
     */
    public static <T> List<T> findAll(Class<T> tClass, String collectionName) {
        return mongodbUtils.mongoTemplate.findAll(tClass, collectionName);
    }

    /**
     * 查询集合下数据条数
     *
     * @param collectionName
     * @return
     */
    public static Long findCount(String collectionName) {
        return mongodbUtils.mongoTemplate.getCollection(collectionName).count();
    }
}