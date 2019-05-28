package com.moyu.crawler.beike.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @Auther: wishm
 * @Date: 2019/5/13 10:01
 * @Description: 根据网络地址下载图片 到本地
 */
public class IOUtil {

    static String path= "C:\\Users\\wishm\\Pictures\\beike\\";
    //图片格式
    static String filePattern = ".jpg";
    public static String savePicture(String urlStr) throws IOException {
        //设置访问地址
        URL url = new URL(urlStr);
        //打开地址访问链接
        URLConnection urlConnection = url.openConnection();
        //网络通道 获取输入 通道
        InputStream inputStream = urlConnection.getInputStream();

        //[0，1)，也就是说，从0（包括0）往上，但是不包括1（排除1）
        int filePath= ((int)((Math.random()*9+1)*100000));


        String uuid = UUID.randomUUID().toString();
        //文件输出流  将网络输入到此地的数据，输出到本地
        FileOutputStream fileOutputStream = new FileOutputStream(path+filePath + uuid +filePattern);

        //byte 字节 网络专用传输文字
        byte[] bytes = new byte[1024];
        int len;


        while( (len = inputStream.read(bytes) )!= -1){
            fileOutputStream.write(bytes, 0, len);
        }

        fileOutputStream.close();
        inputStream.close();
        return filePath+uuid +filePattern;
    }


}
