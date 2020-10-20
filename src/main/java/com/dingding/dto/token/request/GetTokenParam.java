package com.dingding.dto.token.request;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/27 10:21
 */
@Data
public class GetTokenParam {
    private String appkey;
    private String appsecret;
}
