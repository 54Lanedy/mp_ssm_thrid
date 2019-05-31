package cn.lanedy.service;

import cn.lanedy.dao.RoleDao;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-03-01 16:00
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }

    @Override
    public List<Permission> findRolePermissionByRoleId(Long id) {
        return roleDao.findRolePermissionByRoleId(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Long id) {
        return roleDao.findPermissionByRoleId(id);
    }

    @Override
    public void deleteAllRolePermissions(Long id) {
        roleDao.deleteAllRolePermissions(id);
    }

    @Override
    public void updateUserRole_Id(Role role) {
        roleDao.updateUserRole_Id(role);
    }
    @Override
    public int create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public int delete(Long id) {
        return roleDao.delete(id);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

}
