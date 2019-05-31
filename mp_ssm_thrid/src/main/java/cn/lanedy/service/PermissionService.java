package cn.lanedy.service;

import cn.lanedy.baseInterface.BaseService;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/7/18
 */
@Service
public interface PermissionService extends BaseService<Permission> {

    /**
     * 根据权限id查询其所关联的角色数据
     *
     * @param id
     * @return
     */
    List<Role> findRoleByPermissionId(Long id);

    /**
     * 删除此权限关联的所有角色id
     *
     * @param id
     */
    void deleteAllPermissionsRoles(Long id);

    /**
     * 更新此角色的权限依赖关系
     *
     * @param permissionId
     * @param roleId
     */
    void correlationRoles(Long permissionId, Long roleId);
}
