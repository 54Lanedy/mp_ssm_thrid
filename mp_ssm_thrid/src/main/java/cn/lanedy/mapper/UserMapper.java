package cn.lanedy.mapper;

import cn.lanedy.baseInterface.BaseMapper;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:36
 */
public interface UserMapper extends BaseMapper<User> {
    List<Role> findRolesByUsername(String username);
    List<Permission> findPermissionsByUsername(String username);
    int deleteAllUserRoles(Long id);
    void correlationRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);
    boolean exists(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<Role> findRoles(String username);
}
