package com.dingding.dto.message.request;

import lombok.Data;

import java.util.List;

/**
 * @author zxb
 * @date 2019/9/27 16:06
 */
@Data
public class CreateGroupTalkParam {
    private String accessToken;
    private String name;
    private String owner;
    private List<String> userIdList;


}
