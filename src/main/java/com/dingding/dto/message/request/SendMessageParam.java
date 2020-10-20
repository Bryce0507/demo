package com.dingding.dto.message.request;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/27 11:06
 */
@Data
public class SendMessageParam {
 /*   private Number agentId;

    private String userIdList;

    private String deptIdList;
    private Boolean toAllUser;*/

    private String userId;
    private String content;
    private String title;
    private String messageUrl;
    private String linkPicUrl;
    private String msgType;

}
