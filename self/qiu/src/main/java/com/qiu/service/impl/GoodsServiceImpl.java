package com.qiu.service.impl;

import com.qiu.entity.Goods;
import com.qiu.mapper.GoodsMapper;
import com.qiu.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> storageAlert() {
        return goodsMapper.storageAlert();
    }
}


