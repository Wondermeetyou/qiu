package com.qiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiu.entity.Menu;
import com.qiu.entity.User;
import com.qiu.mapper.UserMapper;
import com.qiu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public IPage pageC(IPage<User> page) {
        return userMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<User> page, Wrapper wrapper) {

        return userMapper.pageCC(page, wrapper);
    }


    @Override
    public User login(User user) {

        return userMapper.getByUsernameAndPassword(user);
    }

    @Override
    public void UpdateToken(@Param("token") String token, @Param("loginUser") User user) {
        userMapper.UpdateToken(token, user);
    }

    @Override
    public User searchbykeyword(String nickname) {
        return userMapper.searchbykeyword(nickname);
    }

    @Override
    public List<Menu> getmenu(Integer id ) {
        return userMapper.getmenu(id);
    }

    @Override
    public List<Menu> getparentmenu() {
        return userMapper.getparentmenu();
    }

    @Override
    public List<Menu> getchildmenu(Integer id) {
        return userMapper.getchildmenu(id);
    }

    @Override
    public void logout(Integer id) {
        userMapper.logout(id);
    }
}



