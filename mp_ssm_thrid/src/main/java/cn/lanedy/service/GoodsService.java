package cn.lanedy.service;

import cn.lanedy.baseInterface.BaseService;
import cn.lanedy.entity.Goods;

import java.util.Map;

/**
 * Created by liyue
 * Time 2019-03-04 17:24
 */
public interface GoodsService extends BaseService<Goods> {
    Map<String, Object> searchFromSolr(Map searchMap);
}
