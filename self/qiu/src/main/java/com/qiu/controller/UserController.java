package com.qiu.controller;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiu.common.JwtUtils;
import com.qiu.common.Result;
import com.qiu.entity.User;
import com.qiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserService menuService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(userService.list());
    }


    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

//    {
//        "id":6,
//            "nickname":"测试添加",
//            "age":19,
//            "phone":888888888,
//            "roleId":2
//    }

//    {
//        "id":6,
//            "username":"test",
//            "nickname":"测试添加",
//            "password":123456,
//            "age":18,
//            "sex":0,
//            "phone":41844111874,
//            "roleId":2,
//            "isvalid":"y"
//    }


    //修改
    @PostMapping("/mod")
    public Result mod(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }
//    {
//        "id":6,
//            "username":"test",
//            "nickname":"测试添加",
//            "password":123456,
//            "age":18,
//            "sex":0,
//            "phone":41844111874,
//            "roleId":2,
//            "isvalid":"y"
//    }


    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }


    @GetMapping("/delete")
    public Result delete(int id) {return Result.success(userService.removeById(id));}



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
            userService.UpdateToken(token, loginUser);
            System.out.println("赋予用户  "+loginUser.getUsername()+"  Token成功！");
            return !list.isEmpty()
                    ? Result.success(list.get(0))
                    : Result.error();
        }
        return Result.error("用户名或密码错误");
    }
//    {
//        "username":"admin",
//        "password":"123456"
//    }

    @PostMapping("/logout")
    public void logout(@RequestBody User user){
        userService.logout(user.getId());
    }

    @PostMapping("/menu")
    public Result menu(@RequestBody User user){
        return Result.success(userService.getmenu(user.getId()));
    }

    @PostMapping("/parentmenu")
    public Result parentmenu(){
        return Result.success(userService.getparentmenu());
    }

    @PostMapping("/childmenu")
    public Result childmenu(User user){
        return Result.success(userService.getchildmenu(user.getId()));
    }


    @PostMapping("/searchnickname")
    public Result searchnickname(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        //模糊查询
        lambdaQueryWrapper.like(User::getNickname, user.getNickname());
        //精准查询
        //lambdaQueryWrapper.eq(User::getNickname,user.getNickname());

        return Result.success(userService.list(lambdaQueryWrapper));
    }


    //nickname查询（模糊、匹配）
//    @PostMapping("/searchbynickname")
//    public User searchbynickname(@RequestBody String keyword){
    //        return userService.searchbykeyword(keyword);
//        if(userService.searchbykeyword(keyword) == null) {
//            return Result.success("无对应数据");
//        }

    @PostMapping("/searchbynickname")
    public Result searchbynickname(@RequestParam String nickname) {
        return Result.success(userService.searchbykeyword(nickname));
    }


    //搜索用户信息
    @PostMapping("/searchbyid")
    //id搜索
    public Result searchbyid(@RequestBody JSONObject keyword) {
//        if(userService.getById(keyword.getInteger("keyword")) == null){
//            return Result.success("无对应数据！");
//        }
        return Result.success(userService.getById(keyword.getInteger("keyword")));
    }



//    {
//        "pageNum":1,
//        "pageSize":10,
//        "param":{
//            "keyword":
//        }
//    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody JSONObject query) {
        Page<User> page = new Page<>();
        page.setCurrent(query.getInteger("pageNum"));
        page.setSize(query.getInteger("pageSize"));
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getNickname, query.getJSONObject("param").getString("keyword"));

        IPage result = userService.page(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());
        if(result.getCurrent()>result.getPages())
            result.setCurrent(result.getPages());
            result = userService.page(page, lambdaQueryWrapper);

//      return result.getRecords();
//        return Result.success(result.getRecords());
        return Result.success(result);
    }



    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody JSONObject query) {

        Page<User> page = new Page();
        page.setCurrent(query.getInteger("pageNum"));
        page.setSize(query.getInteger("pageSize"));

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getNickname, query.getJSONObject("param").getString("nickname"));

        IPage result = userService.pageC(page);
        System.out.println(result.toString());
//        IPage result = userService.pageCC(page,lambdaQueryWrapper);
//        System.out.println(result.toString());

        System.out.println("total==" + result.getTotal());


//        return result.getRecords();
        return Result.success(result.getRecords());
    }

    public UserService getMenuService() {
        return menuService;
    }

    public void setMenuService(UserService menuService) {
        this.menuService = menuService;
    }
}

