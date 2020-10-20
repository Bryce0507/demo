package com.dingding.web;

import com.dingding.entity.UserDO;
import com.dingding.util.IpUtil;
import com.taobao.api.ApiException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zxb
 * @date 2019/10/24 14:16
 */

@RestController
@RequestMapping("ip")
@EnableScheduling
public class IpController {


    @RequestMapping("/getIpAddr")
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }


    //自动化发布
    @Scheduled(cron = "0 0 0/1 * * ?")//每天小时执行一次
    @RequestMapping(value = "autoIssue", method = RequestMethod.POST)
    public void autoIssue()  {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = simpleDateFormat.format(date);
        System.out.println("执行时间 = " + d);
    }



}



