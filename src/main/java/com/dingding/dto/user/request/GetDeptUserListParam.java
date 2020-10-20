package com.dingding.dto.user.request;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/28 16:39
 */
@Data
public class GetDeptUserListParam {
    private String accessToken;
    private Long departmentId;

}
