package com.qiu.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiu.entity.Menu;
import com.qiu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService extends IService<User> {

    IPage pageC(IPage<User> page);

    IPage pageCC(IPage<User> page, Wrapper wrapper);

    User login(User user);

    void UpdateToken(@Param("token") String token, @Param("loginUser") User user);

    User searchbykeyword(String nickname);


    List<Menu> getmenu(Integer id);

    List<Menu> getparentmenu();

    List<Menu> getchildmenu(Integer id);

    void logout(Integer id);
}


