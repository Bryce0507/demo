package com.dingding.web;

import com.dingding.dto.department.response.DepartmentResponse;
import com.dingding.dto.department.resquest.DepartmentParam;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxb
 * @date 2019/9/28 16:20
 */
@RequestMapping("department")
@RestController
public class DepartmentController {

    @RequestMapping("getDepartmentList")
    public List<OapiDepartmentListResponse.Department> getDepartmentDetail(@RequestBody DepartmentParam departmentParam) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(departmentParam.getId());
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, departmentParam.getAccessToken());
        return response.getDepartment();
    }

}
