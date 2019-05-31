package cn.lanedy.dao;

import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import cn.lanedy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:50
 */
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int create(User user) {

        return userMapper.create(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return userMapper.findRolesByUsername(username);
    }

    @Override
    public List<Permission> findPermissionsByUsername(String username) {
        return userMapper.findPermissionsByUsername(username);
    }

    @Override
    public int deleteAllUserRoles(Long id) {
        return userMapper.deleteAllUserRoles(id);
    }

    /**
     * 判断当前的用户和角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    public boolean exists(Long userId, Long roleId) {
        return userMapper.exists(userId, roleId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId: roleIds) {
            if (!exists(userId, roleId)) {
                userMapper.correlationRoles(userId, roleId);
            }
        }
    }

    @Override
    public List<Role> findRoles(String username) {
        return userMapper.findRoles(username);
    }


}
