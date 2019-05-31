package cn.lanedy.service;

import cn.lanedy.dao.GoodsDao;
import cn.lanedy.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue
 * Time 2019-03-04 17:25
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int create(Goods goods) {
        return goodsDao.create(goods);
    }

    @Override
    public int delete(Long id) {
        return goodsDao.delete(id);
    }

    @Override
    public int update(Goods goods) {
        return goodsDao.update(goods);
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsDao.findById(id);
    }

    @Override
    public Goods findByName(String name) {
        return goodsDao.findByName(name);
    }

    @Override
    public Map<String, Object> searchFromSolr(Map searchMap) {
        Map<String,Object> map = new HashMap<>();

        return map;
    }
}
