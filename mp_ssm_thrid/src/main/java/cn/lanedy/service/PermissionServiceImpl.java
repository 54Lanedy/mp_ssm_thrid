package cn.lanedy.service;

import cn.lanedy.dao.PermissionDao;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/7/18
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission findById(Long id) {
        return permissionDao.findById(id);
    }

    @Override
    public Permission findByName(String name) {
        return null;
    }

    @Override
    public int create(Permission permission) {
        permissionDao.create(permission);
        return 1;
    }

    @Override
    public int delete(Long id) {
        permissionDao.delete(id);
        return 1;
    }

    @Override
    public int update(Permission permission) {
        permissionDao.update(permission);
        return 1;
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Role> findRoleByPermissionId(Long id) {
        return permissionDao.findRoleByPermissionId(id);
    }

    @Override
    public void deleteAllPermissionsRoles(Long id) {
        permissionDao.deleteAllPermissionsRoles(id);
    }

    @Override
    public void correlationRoles(Long permissionId, Long roleId) {
        permissionDao.correlationRoles(permissionId,roleId);
    }
}
