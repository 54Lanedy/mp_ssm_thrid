package cn.lanedy.entity;

import java.io.Serializable;

/**
 * Created by liyue
 * Time 2019-02-19 15:08
 */
public class User implements Serializable {
    private Long id; //用户编号
    private String username; //用户名
    private String password; //密码
    private String roleId; //角色列表
    private String salt; //盐

    private Boolean locked = Boolean.FALSE;

    public String getCredentialsSalt(){
        return username+salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
