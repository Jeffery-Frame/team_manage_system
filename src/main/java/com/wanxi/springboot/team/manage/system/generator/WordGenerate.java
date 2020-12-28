package com.wanxi.springboot.team.manage.system.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordGenerate {
    public void WordGenerate(){
        Map<String,Object > dataMap = new HashMap();
        try {
            //姓名
            dataMap.put("name", "name");
            //日期
            dataMap.put("time", "time");
            //学习内容
            dataMap.put("title", "title");
            //学习收获
            dataMap.put("harvest", "harvest");
            // 专业
            dataMap.put("type", "type");
            // 班主任
            dataMap.put("headmaster", "headmaster");
            // 教练
            dataMap.put("teacher", "teacher");
            //Configuration 用于读取ftl文件
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");

            /**
             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
             */
            //我的路径是F：/idea_demo/日报.ftl
            configuration.setDirectoryForTemplateLoading(new File("F:\\idea_demo"));

            //输出文档路径及名称
            File outFile = new File("C:\\Users\\吴智渊\\Desktop\\日报.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("日报.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
