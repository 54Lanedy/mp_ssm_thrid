package cn.lanedy.entity;

import java.io.Serializable;

/**
 * Created by liyue
 * Time 2019-02-19 15:24
 */
public class RolePermission implements Serializable {
    private Long id;
    private Long roleId;
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
