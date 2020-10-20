package com.dingding.dto.message.request;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/27 16:51
 */
@Data
public class SendGroupMessageParam {
    private String accessToken;
    private String chatId;
    private String content;
}
