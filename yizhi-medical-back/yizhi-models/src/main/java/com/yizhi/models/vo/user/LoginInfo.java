package com.yizhi.models.vo.user;

import lombok.Data;

@Data
public class LoginInfo {
    private String[] roles;
    private String introduction;
    private String name;
    private String avatar;
}
