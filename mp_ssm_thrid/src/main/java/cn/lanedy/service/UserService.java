package cn.lanedy.service;

import cn.lanedy.baseInterface.BaseService;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:42
 */
public interface UserService extends BaseService<User> {

    List<Role> findRolesByUsername(String username);
    List<Permission> findPermissionsByUsername(String username);

    int deleteAllUserRoles(Long id);
    void correlationRoles(Long userId, Long... roleIds);

    List<Role> findRoles(String username);
}
