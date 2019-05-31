package cn.lanedy.realm;

import cn.lanedy.entity.Permission;
import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import cn.lanedy.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liyue
 * Time 2019-02-19 15:06
 */
public class UserRealm extends AuthorizingRealm {//AuthorizingRealm继承了AuthenticatingRealm

    @Autowired
    private UserService userService;

    /**
     * 身份校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("正在进行身份验证doGetAuthenticationInfo...");
        //获取用户身份的唯一标识，账号、邮箱等，唯一即可
        String username = (String) token.getPrincipal();
        //与数据库的用户作对比验证
        User user = userService.findByName(username);
        if (user == null) {
            throw new UnknownAccountException();//没有该用户
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();//账号被锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()//当前 Realm 的名称
        );
        return authenticationInfo;
    }

    /**
     * 权限校验
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("正在执行权限校验doGetAuthorizationInfo...");

        //primary principal 是在整个应用程序中用户（subject）的唯一标识,如账号、邮箱等
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //注意这里的setRoles和setStringPermissions需要的都是一个Set<String>类型参数
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        List<Role> roless = userService.findRolesByUsername(username);
        if (roless != null && roless.size()>0) {
            for (Role role : roless) {
                roles.add(role.getRole());
            }
        }

        List<Permission> permissionss = userService.findPermissionsByUsername(username);
        if (permissionss != null && permissionss.size()>0) {
            for (Permission permission : permissionss) {
                permissions.add(permission.getPermission());
            }
        }

        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}
