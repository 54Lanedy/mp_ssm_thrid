package cn.lanedy.mapper;

import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/7/18
 */
public interface PermissionMapper {
    
    void create(Permission permission);

    void deleteRolePermission(Long permissionId);

    void deletePermission(Long permissionId);

    List<Permission> findAll();

    void update(Permission permission);

    Permission findById(Long id);

    List<Role> findRoleByPermissionId(Long id);

    void deleteAllPermissionsRoles(Long id);

    void correlationRoles(@Param("permissionId") Long permissionId, @Param("roleId") Long roleId);
}
