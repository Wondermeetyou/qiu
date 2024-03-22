package com.qiu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiu.entity.Menu;
import com.qiu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getByUsernameAndPassword(User user);

    IPage pageC(IPage<User> page);

    IPage pageCC(IPage<User> page, Wrapper wrapper);

    void UpdateToken(@Param("token") String token, @Param("loginUser") User user);

    User searchbykeyword(@Param("nickname") String nickname);


    List<Menu> getmenu(Integer id);

    List<Menu> getparentmenu();

    List<Menu> getchildmenu(Integer id);

    void logout(Integer id);
}

