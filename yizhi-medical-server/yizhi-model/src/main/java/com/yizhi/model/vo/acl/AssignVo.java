package com.yizhi.model.vo.acl;

import lombok.Data;

@Data
public class AssignVo {

    private Long roleId;

    private Long[] permissionId;
}
