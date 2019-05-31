package cn.lanedy.service;

import cn.lanedy.dao.UserDao;
import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import cn.lanedy.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-02-19 15:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public int create(User user) {
        //创建的时候加密密码
        passwordHelper.encryptPassword(user);
        return userDao.create(user);
    }

    @Override
    public int delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public int update(User user) {
        //修改的时候加密密码
        passwordHelper.encryptPassword(user);
        return userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return userDao.findRolesByUsername(username);
    }

    @Override
    public List<Permission> findPermissionsByUsername(String username) {
        return userDao.findPermissionsByUsername(username);
    }

    @Override
    public int deleteAllUserRoles(Long id) {
        return userDao.deleteAllUserRoles(id);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    /**
     * 根据用户名查找其角色
     */
    @Override
    public List<Role> findRoles(String username) {
        return userDao.findRoles(username);
    }
}
