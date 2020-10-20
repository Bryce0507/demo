package com.dingding.dto.department.response;

import lombok.Data;

/**
 * @author zxb
 * @date 2019/9/28 16:23
 */
@Data
public class DepartmentResponse {
    private Integer id;
    private String name;
    private Integer parentId;
    private boolean createDeptGroup;
    private boolean autoAddUser;
}
