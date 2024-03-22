package com.qiu.controller;

import com.qiu.common.JwtUtils;
import com.qiu.common.Result;
import com.qiu.entity.User;
import com.qiu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    //依赖业务层对象
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        //调用业务层：登录功能
        User loginUser = userService.login(user);

        List<User> list = userService.lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()).list();

        //判断：登录用户是否存在
        if (loginUser != null) {
            //自定义信息
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            claims.put("nickname", loginUser.getNickname());

            //使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);
//          System.out.println(token);
            userService.UpdateToken(token, loginUser);
//            System.out.println("赋予用户  "+loginUser.getUsername()+"  Token成功！");
            return !list.isEmpty()
                    ? Result.success("Bearer " + list.get(0).getToken())
                    : Result.error();
//          return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }
}
