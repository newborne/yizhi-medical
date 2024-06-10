package com.yizhi.service.medical.controller.admin;

import com.yizhi.model.pojo.acl.Login;
import com.yizhi.model.pojo.acl.LoginInfo;
import com.yizhi.util.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录")
@RestController
@RequestMapping("/admin/medical/acl/user")
//@CrossOrigin
public class AclController {

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login() {
        Login login = new Login();
        login.setToken("admin-token");
        return Result.ok(login);
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result info() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRoles(new String[]{"admin"});
        loginInfo.setIntroduction("I am a super administrator");
        loginInfo.setName("Super Admin");
        loginInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(loginInfo);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public Result logout() {
        Login login = new Login();
        login.setToken("");
        return Result.ok("success");
    }
}


