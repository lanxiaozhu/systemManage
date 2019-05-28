package com.moyu.apiweb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.springBena.OrderConfig;
import com.moyu.apiweb.springBena.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiWebApplicationTests extends TestAb{



    @Test
    public void tests(){
        /*String exePath = "C:\\Users\\wishm\\Downloads\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://toolsqa.com/automation-practice-form/");
        //driver.get("https:/www.baidu.com");*/
    }





    //volatile
    private volatile boolean flag = true;

    private void setI(boolean state) {
        System.out.println("线程设置了stop begin");
        this.flag = state; // 赋值 可见性测试，是否会使用高速缓存中的变量
        System.out.println("线程设置了stop end");
    }

    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("进入方法" + flag);
            while (flag) {
                System.out.println("方法执行");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程结束");

        });

        t1.start();

        Thread.sleep(2000);
        setI(false);
    }


    /**
     * 递归读取所有的key
     *
     * @param jsonObject
     */
    public static StringBuffer getAllKey(JSONObject jsonObject) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> keys = jsonObject.keySet().iterator();// jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            stringBuffer.append(key.toString()).append("|");
            if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject innerObject = (JSONObject) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            } else if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray innerObject = (JSONArray) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getAllKey(JSONArray json1) {
        StringBuffer stringBuffer = new StringBuffer();
        if (json1 != null) {
            Iterator i1 = json1.iterator();
            while (i1.hasNext()) {
                Object key = i1.next();
                if (key instanceof JSONObject) {
                    JSONObject innerObject = (JSONObject) key;
                    stringBuffer.append(getAllKey(innerObject));
                } else if (key instanceof JSONArray) {
                    JSONArray innerObject = (JSONArray) key;
                    stringBuffer.append(getAllKey(innerObject));
                } else {
                }
            }
        }
        return stringBuffer;
    }

    private final static String st1 = "{\"username\":\"tom\",\"age\":18,\"address\":[{\"province\":\"上海市\"},{\"city\":\"上海市\"},{\"disrtict\":\"静安区\"}]}";
    private final static String st2 = "{username:\"tom\",age:18}";


    @Test
    public void main() {
        System.out.println(st1);
        JSONObject jsonObject1 = JSONObject.parseObject(st1);
        StringBuffer stb = getAllKey(jsonObject1);
        System.err.println(stb);


    }

    @Test
    public void test1() {
        String data = "{\"name\":\"moyu\",\"userInfo\":[\n" +
                "{\"userId\": 1},\n" +
                "{\"address\":\"杭州\"}\n" +
                "]}";
        //parseObject 解析
        JSONObject json = JSONObject.parseObject(data);
        Set<String> strings = json.keySet();
        Iterator<String> iterator = strings.iterator();
        //第一层 json Key
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }


    @Test
    public void testBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(OrderConfig.class);
        Teacher rice = ac.getBean(Teacher.class);
        //resource  get全局搜搜我
    }

    @Test
    public void testEquals() {
       /* HashCodeHelper h = new HashCodeHelper();
        h.id =1;
        h.name="1";

        HashCodeHelper h1 = new HashCodeHelper();
        h1.id =1;
        h1.name="1";

        HashCodeHelper h2 = new HashCodeHelper();
        h2.id =2;
        h2.name="2";

        System.out.println(h.equals(h1));
        //结果是 false


        HashSet<HashCodeHelper> set = new HashSet();
        set.add(h);
        set.add(h1);
        set.add(h2);

        System.out.println(set.size());
        Iterator<HashCodeHelper> iterator = set.iterator();
        while(iterator.hasNext()){
            HashCodeHelper next = iterator.next();
            System.out.println(next);
        }*/
    }

}
