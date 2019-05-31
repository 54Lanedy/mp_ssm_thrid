package cn.lanedy.dao;

import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/7/18
 */
@Component
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void create(Permission permission) {
        permissionMapper.create(permission);
    }

    @Override
    public void delete(Long id) {
        //先将和Permission相关的表数据删除
        permissionMapper.deleteRolePermission(id);

        //再删除Permission表数据
        permissionMapper.deletePermission(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.update(permission);
    }

    @Override
    public Permission findById(Long id) {
        return permissionMapper.findById(id);
    }

    @Override
    public List<Role> findRoleByPermissionId(Long id) {
        return permissionMapper.findRoleByPermissionId(id);
    }

    @Override
    public void deleteAllPermissionsRoles(Long id) {
        permissionMapper.deleteAllPermissionsRoles(id);
    }

    @Override
    public void correlationRoles(Long permissionId, Long roleId) {
        permissionMapper.correlationRoles(permissionId, roleId);
    }
}
