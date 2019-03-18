package com.moyu.tools.genertor;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestGenerator {

    public static void main(String[] args) {
        try {
            System.out.println("start generator ...");
            List<String> warnings = new ArrayList<>();
            boolean overwrite = true;
            String path = TestGenerator.class.getResource("/").getPath();
            System.out.println(path);
            File configFile = new File(path + "user.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("end generator!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
