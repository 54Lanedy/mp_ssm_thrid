package cn.lanedy.baseInterface;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:41
 */
public interface BaseService<T> {

    /**
     * 创建用户
     *
     * @param t
     */
    int create(T t);

    /**
     * 根据用户ID删除用户信息
     *
     * @param id
     */
    int delete(Long id);

    /**
     * 更新用户信息
     *
     * @param t
     */
    int update(T t);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据ID查询其所有数据
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 根据名称查找
     */
    T findByName(String name);
}
