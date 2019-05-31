package cn.lanedy.baseInterface;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:44
 */
public interface BaseDao<T> {
    /**
     * 注册、新建
     */
    int create(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新
     * @param t
     * @return
     */

    /**
     * 查找全部
     * @return
     */
    List<T> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 根据名称查找
     * @param name
     * @return
     */
    T findByName(String name);

    /**
     * 修改
     */
    int update(T t);
}
