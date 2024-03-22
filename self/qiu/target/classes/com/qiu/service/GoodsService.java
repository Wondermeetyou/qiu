package com.qiu.service;

import com.qiu.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsService extends IService<Goods> {

    List<Goods>  storageAlert();

}


