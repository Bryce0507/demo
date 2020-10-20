package com.dingding.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zxb
 * @date 2019/10/23 9:13
 */
@RestController
@RequestMapping("mail")
public class MailController {

    @RequestMapping("sendMail")
    public void sendMail() throws IOException {
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("13862258153@163.com");
        account.setUser("13862258153");
        account.setPass("zxbzxb123");
        URL url = new URL("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=0&pn=14&spn=0&di=42130&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=399355957%2C4288497410&os=433393464%2C459632754&simid=4191585588%2C613137579&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic22.nipic.com%2F20120629%2F9363864_163739336188_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3FdAzdH3Fd0AzdH3Fm9n0bbmhaab1j008_z%26e3Bip4s&gsm=&islist=&querylist=");
//        URLConnection connection = url.openConnection();
//        InputStream inputStream = connection.getInputStream();
        File file = FileUtil.file(url);
//        File file = new File("C:/Users/Administrator/desktop/微信图片_20190924104421.jpg");
        MailUtil.send(account,"834388682@qq.com","测试","aaaaaaaaaaaa",false, file);
    }
}
