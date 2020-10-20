package com.dingding.service;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zxb
 * @date 2020/8/12 11:37
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        findAll(new File("/root/user/tim/log"));


    }

    public  static String findAll(File file ) throws IOException {

        StringBuilder build = new StringBuilder();
        if(!file.exists())System.out.print("文件不存在！");
        //判断是否是文件  并且是否是.log文件
        if (file.isFile() && file.getName().endsWith(".log")) {
            //获取输入流
            FileInputStream is = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            //获取reader
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //根据reader.readLine 是否有下一行判断
            while (bufferedReader.readLine() != null) {
                //获取这行中是否包含ERROR 或者error
                if (bufferedReader.readLine().contains("error") || bufferedReader.readLine().contains("error")) {
                    build.append(bufferedReader.readLine()).append("\n");
                }
            }
        } else {
            //如果不是文件就是文件夹  那么 获取文件夹下的所有文件
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("no file");
            } else {
                //递归获取
                for (File f : files) {
                    findAll(f);
                }
            }
        }
        return build.toString();
    }



}
