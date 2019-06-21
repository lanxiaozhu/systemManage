package com.moyu.util.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/30 15:05
 * @Description:
 */
public class WebUtil {
    /** -- 聚合数据URL -- */
    private static final String JUHE_DATA_API_URL = "http://apis.juhe.cn/ip/ip2addr";

    /** -- 聚合数据key -- */
    private static final String JUHE_DATA_API_KEY = "942fb48c90bddcb85b61d6908c566dc1";
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;

    /**
     * 获取访问ip
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!isEffectiveRemoteAddr(ip)) {
            if (!isEffectiveRemoteAddr(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (!isEffectiveRemoteAddr(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (!isEffectiveRemoteAddr(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (!isEffectiveRemoteAddr(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (!isEffectiveRemoteAddr(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip != null && ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
    /**
     * 远程地址是否有效.
     *
     * @param remoteAddr 远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private static boolean isEffectiveRemoteAddr(final String remoteAddr) {
        if (remoteAddr == null || "".equals(remoteAddr.trim()) || "unknown".equalsIgnoreCase(remoteAddr.trim())){
            return false;
        }
        return true;
    }
    /**
     * 根据IP获取IP归属地
     *
     * @param ip
     * @return
     */
    public static String getIpAddress(String ip) {
        if (StringUtils.isEmpty(ip) || !checkIp(ip)) {
            return "unknown";
        }
        String result = null;
        String url = JUHE_DATA_API_URL;// 请求接口地址
        Map<String, String> params = new HashMap<String, String>();// 请求参数
        params.put("ip", ip);// 需要查询的IP地址
        params.put("key", JUHE_DATA_API_KEY);// 应用APPKEY(应用详细页查询)
        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getIntValue("error_code") == 0) {
                result = object.getJSONObject("result").getString("area")
                        + object.getJSONObject("result").getString("location");
            } else {
                result = "unknown";
            }
        } catch (Exception e) {
            result =  "unknown";
        }
        return result;
    }
    private static boolean checkIp(String ip) {
        if (StringUtils.isEmpty(ip) || isBlank(ip)) {
            return false;
        }
        return checkString(ip, "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    }
    private static boolean checkString(String str, String regex) {
        return str.matches(regex);
    }
    /**
     *
     * @param strUrl
     *            请求地址
     * @param params
     *            请求参数
     * @param method
     *            请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    private static String net(String strUrl, Map<String, String> params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    // 将map型转为请求参数型
    private static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (@SuppressWarnings("rawtypes")
                Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**
     * 判断是否有空白字符
     *
     * @param str
     * @return
     */
    private static boolean isBlank(String str) {
        if (StringUtils.isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
