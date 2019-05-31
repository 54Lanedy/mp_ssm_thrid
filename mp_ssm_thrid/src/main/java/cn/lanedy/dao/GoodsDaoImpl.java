package cn.lanedy.dao;

import cn.lanedy.entity.Goods;
import cn.lanedy.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-03-04 17:21
 */
@Component
public class GoodsDaoImpl implements GoodsDao {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public int create(Goods goods) {
        return goodsMapper.create(goods);
    }

    @Override
    public int delete(Long id) {
        return goodsMapper.delete(id);
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public Goods findByName(String name) {
        return goodsMapper.findByName(name);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.update(goods);
    }
}
