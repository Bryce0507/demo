package com.dingding.web;

import com.dingding.dto.token.request.GetTokenParam;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxb
 * @date 2019/9/27 10:08
 */
@RestController
@RequestMapping(value = "/token")
public class TokenController {


    @RequestMapping(value = "/getToken",method = RequestMethod.POST)
    public String getAccessToken(@RequestBody GetTokenParam getTokenParam) throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(getTokenParam.getAppkey());
        request.setAppsecret(getTokenParam.getAppsecret());
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        String accessToken = response.getAccessToken();
        return accessToken;
    }
}
