package com.qiu.mapper;

import com.qiu.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> storageAlert();
}

