package com.moyu.apiweb.apiweb.controller.api;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: guoxianjun
 * @Date: 2019/2/13 17:52
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class TestCon {


    private static OkHttpClient mHttpClient = new OkHttpClient.Builder().build();

    @GetMapping("/q")
    public String q() throws IOException {

        //map 封装参数
        Map<String, Object> params = new HashMap<>();
        params.put("name", "name");
        params.put("idCardNum", "idCardNum");
        //丢出 map  和  ACCESS_SECRET  做出外露接口 ,构建模式

        Request request = new Request.Builder().url("http://127.0.0.1:8089/login/logout").post(rsBuilder(params)).build();

        Response response = mHttpClient.newCall(request).execute();
        Integer code = response.code();
        String body = response.body().string();
        System.out.println(code);
        System.out.println(body);
        return "ok";
    }

    // 签名超时时长，默认时间为5分钟，ms
    private static final int SIGN_EXPIRED_TIME = 5 * 60 * 1000;

    private static final String API_SIGN_KEY_CONFIG_PATH = "/mop/common/system/api_sign_key_mapping.properties";

    private static final String SIGN_KEY = "sign";

    private static final String TIMESTAMP_KEY = "timestamp";

    private static final String ACCESS_KEY = "accessKey";

    private static final String ACCESS_SECRET = "accessSecret";
    //对方提供  accessKey  和 accessSecret
    // 我方传入 接口参数 和 时间戳   accessKey  和 accessSecret
    private FormBody rsBuilder(Map<String, Object> params) throws UnsupportedEncodingException {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("accessKey",ACCESS_KEY);
        builder.add("sign", createSign(builder, params, this.ACCESS_SECRET));
        return builder.build();
    }

    private String createSign(FormBody.Builder builder, Map<String, Object> params, String accessSecret) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
                builder.add(key.toString(), value.toString());
            }
            temp.append(valueString);
        }
        temp.append("&").append(ACCESS_SECRET).append("=").append(accessSecret);
        return getMD5Str(temp.toString()).toUpperCase();
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String getMD5Str(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            //throw new Exception("MD5加密出现错误，"+e.toString());
        }
        return null;
    }
}
