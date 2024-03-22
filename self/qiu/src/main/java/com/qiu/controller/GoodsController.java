package com.qiu.controller;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiu.common.Result;
import com.qiu.entity.Goods;
import com.qiu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(goodsService.list());
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return Result.success(goodsService.save(goods));
    }

    //修改
    @PostMapping("/mod")
    public Result mod(@RequestBody Goods goods) {
        return Result.success(goodsService.updateById(goods));
    }

    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Goods goods) {
        return Result.success(goodsService.saveOrUpdate(goods));
    }

    //删除
    @GetMapping("/delete")
    public Result delete(int goods_id) {return Result.success(goodsService.removeById(goods_id));}


    //按商品名查询
    @PostMapping("/searchgoodsname") //用goodsName
    public Result searchgoodsname(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        //模糊查询
        lambdaQueryWrapper.like(Goods::getGoodsName, goods.getGoodsName());
        //精准查询
        //lambdaQueryWrapper.eq(User::getNickname,user.getNickname());

        return Result.success(goodsService.list(lambdaQueryWrapper));
    }


    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody JSONObject query) {
        Page<Goods> page = new Page<>();
        page.setCurrent(query.getInteger("pageNum"));
        page.setSize(query.getInteger("pageSize"));
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Goods::getGoodsName, query.getJSONObject("param").getString("keyword"));

        IPage result = goodsService.page(page, lambdaQueryWrapper);
        System.out.println("共有 " + result.getTotal()+" 条数据");
        if(result.getCurrent()>result.getPages())
            result.setCurrent(result.getPages());
            result = goodsService.page(page, lambdaQueryWrapper);
        return Result.success(result);
    }

    //查询库存不足的商品
    @GetMapping("/storageAlert")
    public List<Goods> storageAlert(){
        return goodsService.storageAlert();
    }

    @PostMapping("/Alert")
    public Result Alert(@RequestBody JSONObject query) {
        Page<Goods> page = new Page<>();
        page.setCurrent(query.getInteger("pageNum"));
        page.setSize(query.getInteger("pageSize"));
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.le(Goods::getGoodsStock, query.getJSONObject("param").getString("keyword"));

        IPage result = goodsService.page(page, lambdaQueryWrapper);
        System.out.println("共有 " + result.getTotal()+" 条数据");
        if(result.getCurrent()>result.getPages())
            result.setCurrent(result.getPages());
        result = goodsService.page(page, lambdaQueryWrapper);
        return Result.success(result);
    }
}

