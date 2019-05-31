package cn.lanedy.controller;

import cn.lanedy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by liyue
 * Time 2019-03-07 14:45
 */
@RestController
@RequestMapping(value = "/goods/")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "search")
    public Map<String,Object> searchGoods(@RequestBody Map<String,Object> searchMap){
        Map<String, Object> listMap = goodsService.searchFromSolr(searchMap);
        return listMap;
    }
}
