package cn.lanedy.mapper;

import cn.lanedy.baseInterface.BaseMapper;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-03-01 15:56
 */
public interface RoleMapper extends BaseMapper<Role> {
    void correlationPermissions(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void uncorrelationPermissions(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    boolean exists(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    List<Permission> findRolePermissionByRoleId(Long id);

    List<Permission> findPermissionByRoleId(Long id);

    void deleteAllRolePermissions(Long id);

    void updateUserRole_Id(Role role);

    void deleteUserRole(Long roleId);

    void deleteRole(Long roleId);
}
