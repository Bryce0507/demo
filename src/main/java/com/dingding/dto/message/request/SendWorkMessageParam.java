package com.dingding.dto.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/27 15:37
 */

@Data
@AllArgsConstructor
public class SendWorkMessageParam {
    private Long agentId;

    private String userIdList;
    private String deptIdList;

    private Boolean toAllUser;
    private String msg;

    private String accessToken;

    private String content;

}
