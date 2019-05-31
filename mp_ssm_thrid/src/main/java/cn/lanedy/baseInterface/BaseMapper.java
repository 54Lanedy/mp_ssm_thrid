package cn.lanedy.baseInterface;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:30
 * 通用方法
 */
public interface BaseMapper<T> {
    int create(T t);
    int delete(Long id);
    int update(T t);
    List<T> findAll();

    T findById(Long id);
    T findByName(String name);

}
