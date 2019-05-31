package cn.lanedy.dao;

import cn.lanedy.baseInterface.BaseDao;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-03-01 15:55
 */
public interface RoleDao extends BaseDao<Role> {
    void correlationPermissions(Long roleId, Long[] permissionIds);

    void uncorrelationPermissions(Long roleId, Long[] permissionIds);

    List<Permission> findRolePermissionByRoleId(Long id);

    List<Permission> findPermissionByRoleId(Long id);

    void deleteAllRolePermissions(Long id);

    void updateUserRole_Id(Role role);
}
