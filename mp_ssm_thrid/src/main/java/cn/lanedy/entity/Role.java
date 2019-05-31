package cn.lanedy.entity;

import java.io.Serializable;

/**
 * Created by liyue
 * Time 2019-02-19 15:15
 */
public class Role implements Serializable {
    private Long id; //角色编号
    private String role; //角色标识 程序中判断使用，如"admin"
    private String description; //角色描述，UI界面显示使用
    private Long pid; //父节点id值
    private Boolean available = Boolean.FALSE; //是否可用，如果不可用将不会添加给用户


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
