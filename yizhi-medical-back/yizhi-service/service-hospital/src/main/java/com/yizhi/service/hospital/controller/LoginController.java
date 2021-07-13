package com.yizhi.service.hospital.controller;

import com.yizhi.common.util.result.Result;
import com.yizhi.vo.user.Login;
import com.yizhi.vo.user.LoginInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vue-admin-template/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public Result login(){
        Login login=new Login();
        login.setToken("admin-token");
        return Result.ok(login);
    }

    @GetMapping("/info")
    public Result info(){
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setRoles(new String[]{"admin"});
        loginInfo.setIntroduction("I am a super administrator");
        loginInfo.setName("Super Admin");
        loginInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(loginInfo);
    }

    @PostMapping("/logout")
    public Result logout(){
        Login login=new Login();
        login.setToken("");
        return Result.ok("success");
    }

}


