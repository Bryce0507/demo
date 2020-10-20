package com.dingding.util;

/**
 * @author zxb
 * @date 2020/2/27 15:09
 */
public class Demo {
    private static String aa = "aa";
    private static String bb = "bb";
    private static String cc = "cc";


    public static void main(String[] args) {
        System.out.println(aa);
        Integer a = 1;
        boolean b = a.equals(null);
        System.out.println("b = " + b);

        String[] strings = new String[]{"123","2345"};
        int length = strings.length;
        System.out.println("length = " + length);


    }
}
