package com.dingding.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zxb
 * @date 2020/5/29 16:53
 */
@Controller
@RequestMapping("/home")
public class LoginController {


    @RequestMapping("/login")
    public String DingLogin(String code, String state) {
        //state 是前端传入的，钉钉并不会修改，比如有多种登录方式的时候，一个登录方法判断登录方式可以进行不同的处理。

        OapiSnsGetuserinfoBycodeResponse response = new OapiSnsGetuserinfoBycodeResponse();
        try {
            String qrAppId = "dingoaiuqjlozkjnaemzn2";
            String qrAppSecret = "NscmBFE6dRH_nIV1d4eLnlEQlybqWRcwNjqQAavvyJKz7jECuaaDrkktaLty9t-5";
            if (StringUtils.isBlank(qrAppId) || StringUtils.isBlank(qrAppSecret)) {
                throw new Exception("请先配置钉钉扫码登录信息！");
            }

            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
            OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
            req.setTmpAuthCode(code);
            response = client.execute(req, qrAppId, qrAppSecret);

            //获取到response后就可以进行自己的登录业务处理了

            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            //此处省略一万行代码


        } catch (Exception e) {
            response.setErrmsg(e.getMessage());
        }

        return response.getUserInfo().getUnionid();
    }
}
