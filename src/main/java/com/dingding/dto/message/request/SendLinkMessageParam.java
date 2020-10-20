package com.dingding.dto.message.request;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/28 17:17
 */
@Data
public class SendLinkMessageParam {
    private Long agentId;
    private String userIdList;
    private String deptIdList;
    private Boolean toAllUser;
    private String accessToken;

}
