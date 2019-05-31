package cn.lanedy.dao;

import cn.lanedy.baseInterface.BaseDao;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:43
 */
public interface UserDao extends BaseDao<User> {
    List<Role> findRolesByUsername(String username);
    List<Permission> findPermissionsByUsername(String username);
    int deleteAllUserRoles(Long id);
    //相关角色
    void correlationRoles(Long userId, Long... roleIds);

    List<Role> findRoles(String username);
}
