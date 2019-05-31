package cn.lanedy.entity;

import java.io.Serializable;

/**
 * Created by liyue
 * Time 2019-02-19 15:18
 */
public class Permission implements Serializable {
    private Long id; //权限编号
    private String permission; //权限标识 程序中判断使用，如"user:create"
    private String description; //权限描述。UI界面显示用
    private Long rid; //此权限关联的角色的id
    private Boolean available = Boolean.FALSE; //是否可用，如果不可用将不会添加给用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
