package com.dingding.web;

import com.alibaba.fastjson.JSONObject;
import com.dingding.dto.message.request.*;
import com.dingding.dto.message.response.MessageResult;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatCreateRequest;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiChatCreateResponse;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author zxb
 * @date 2019/9/27 11:02
 */
@RestController
@RequestMapping("message")
public class MessageController {

/*    @Autowired
    private RestTemplate restTemplate;*/

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public MessageResult sendMessage(@RequestBody SendMessageParam sendMessageParam) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        String url = "http://testdingding.52rental.com/dingding/api/sendDingTalkMsg";
/*        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", sendMessageParam.getUserId());
        jsonObject.put("content", sendMessageParam.getContent());
        jsonObject.put("title", sendMessageParam.getTitle());
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toJSONString(), headers);*/
//        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        MessageResult messageResult = restTemplate.postForObject(url, sendMessageParam, MessageResult.class);
        System.out.println(messageResult);

        return messageResult;
    }

    @RequestMapping(value = "sendWorkMessage", method = RequestMethod.POST)
    public Long sendWorkMessage(@RequestBody SendWorkMessageParam sendWorkMessageParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(sendWorkMessageParam.getUserIdList());
        request.setAgentId( sendWorkMessageParam.getAgentId());
        request.setToAllUser(sendWorkMessageParam.getToAllUser());


        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent(sendWorkMessageParam.getContent());
        request.setMsg(msg);

        OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, sendWorkMessageParam.getAccessToken());
        return response.getTaskId();
    }

    @RequestMapping(value = "createGroupTalk")
    public String createGroupTalk(@RequestBody CreateGroupTalkParam createGroupTalkParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/create");
        OapiChatCreateRequest request = new OapiChatCreateRequest();
        request.setName(createGroupTalkParam.getName());
        request.setOwner(createGroupTalkParam.getOwner());
        request.setUseridlist(createGroupTalkParam.getUserIdList());
        OapiChatCreateResponse response = client.execute(request, createGroupTalkParam.getAccessToken());
        return response.getChatid();
    }

    @RequestMapping(value = "sendGroupMessage")
    public String sendGroupMessage(@RequestBody SendGroupMessageParam sendGroupMessageParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/send");
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid(sendGroupMessageParam.getChatId());

        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        msg.setMsgtype("text");
        OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
        text.setContent(sendGroupMessageParam.getContent());
        msg.setText(text);

        request.setMsg(msg);
        OapiChatSendResponse response = client.execute(request, sendGroupMessageParam.getAccessToken());
        return response.getMessageId();
    }

    @RequestMapping(value = "sendLinkMessage", method = RequestMethod.POST)
    public Long sendLinkMessage(@RequestBody SendLinkMessageParam sendLinkMessageParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(sendLinkMessageParam.getUserIdList());
        request.setAgentId( sendLinkMessageParam.getAgentId());
        request.setToAllUser(sendLinkMessageParam.getToAllUser());


        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("link");
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle("报价单");
        msg.getLink().setText("报价单编号为LRQO-20190928-00006的标价单详情");
        msg.getLink().setMessageUrl("http://erp.lr-amm.com/#/quotePriceOrderManage/quotePriceDetailOrder?id=1010");
        msg.getLink().setPicUrl("https://imgsa.baidu.com/news/q%3D100/sign=f8bf3712299759ee4c5064cb82fb434e/5243fbf2b2119313403bacaf6a380cd791238db1.jpg");
        request.setMsg(msg);

        OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, sendLinkMessageParam.getAccessToken());
        return response.getTaskId();
    }





}
