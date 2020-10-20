/*
package com.dingding.listen;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @author zxb
 * @date 2020/4/16 9:32
 *//*

@WebListener()
public class MyServletContextListener implements ServletContextListener {
    private ServletContext sc;
    @Override
    //Application被初始化的时候创建
    public void contextInitialized(ServletContextEvent sce) {
        //创建一个Map，key为IP，value为该IP上所发出的会话的对象
        Map<String, List<HttpSession>> map = new HashMap<>();
        sc = sce.getServletContext();
        //将map放到全局域中
        sc.setAttribute("map",map);
    }

}
*/
