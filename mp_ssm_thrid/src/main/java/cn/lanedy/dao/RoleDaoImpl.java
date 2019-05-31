package cn.lanedy.dao;

import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-03-01 15:56
 */
@Component
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int create(Role role) {
        return roleMapper.create(role);
    }

    @Override
    public int delete(Long id) {
        //先将和角色相关的表删除
        roleMapper.deleteUserRole(id);

        //再删除角色表数据
        roleMapper.deleteRole(id);

        return 1;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleMapper.findByName(name);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public void correlationPermissions(Long roleId, Long[] permissionIds) {
        if(permissionIds == null || permissionIds.length == 0){
            return;
        }
        for(Long permissionId : permissionIds){
            if(!exists(roleId, permissionId)){
                roleMapper.correlationPermissions(roleId,permissionId);
            }
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long[] permissionIds) {
        if(roleId == null || permissionIds.length == 0){
            return;
        }
        for(Long permissionId : permissionIds){
            if(exists(roleId, permissionId)){
                roleMapper.uncorrelationPermissions(roleId, permissionId);
            }
        }
    }

    @Override
    public List<Permission> findRolePermissionByRoleId(Long id) {
        return roleMapper.findRolePermissionByRoleId(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Long id) {
        return roleMapper.findPermissionByRoleId(id);
    }

    @Override
    public void deleteAllRolePermissions(Long id) {
        roleMapper.deleteAllRolePermissions(id);
    }

    @Override
    public void updateUserRole_Id(Role role) {
        roleMapper.updateUserRole_Id(role);
    }

    /**
     * 查询表中是否存在此数据
     * @param roleId
     * @param permissionId
     * @return
     */
    private boolean exists(Long roleId, Long permissionId) {
        return roleMapper.exists(roleId, permissionId);
    }
}
