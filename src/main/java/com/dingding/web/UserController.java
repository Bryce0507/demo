package com.dingding.web;

import com.dingding.dto.user.request.GetDeptUserListParam;
import com.dingding.dto.user.response.GetManagerListParam;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetAdminRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.OapiUserGetAdminResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxb
 * @date 2019/9/27 10:34
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "getManagerList", method = RequestMethod.POST)
    public List<OapiUserGetAdminResponse.AdminList> getManagerList(@RequestBody GetManagerListParam getManagerListParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_admin");
        OapiUserGetAdminRequest request = new OapiUserGetAdminRequest();
        request.setHttpMethod("GET");

        OapiUserGetAdminResponse response = client.execute(request, getManagerListParam.getAccessToken());
        return response.getAdminList();

    }

    @RequestMapping(value = "getDeptUserList", method = RequestMethod.POST)
    public List<OapiUserSimplelistResponse.Userlist> getDeptUserList(@RequestBody GetDeptUserListParam getDeptUserListParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(getDeptUserListParam.getDepartmentId());
        request.setOffset(0L);
        request.setSize(100L);
        request.setHttpMethod("GET");

        OapiUserSimplelistResponse response = client.execute(request, getDeptUserListParam.getAccessToken());
        return response.getUserlist();
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        list.add("www.baidu.com");
        list.add("www.zhonghao.com");
        list.add("www.xiaojie.com");
        list.add("www.dumeng.com");
        list.add("www.baidu.com");
        list.add("www.zhonghao.com");
        list.add("www.xiaojie.com");
        list.add("www.dumeng.com");
        list.add("www.zhonghao.com");
        list.add("www.xiaojie.com");
        list.add("www.xiaojie.com");
        list.add("www.xiaojie.com");
        int count = 0;


        //重复的加入list2集合
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list2.add(list.get(i));
                    break;
                }
            }
        }

        //统计list2集合中重复数据出现次数,对应放入Map集合
        for (String obj : list2) {
            if (map.containsKey(obj)) {
                count++;
                map.put(obj, map.get(obj) + 1);
            } else {
                map.put(obj, 1);
            }
        }
    }

}
