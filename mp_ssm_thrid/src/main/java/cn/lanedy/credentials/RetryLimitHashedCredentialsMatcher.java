package cn.lanedy.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by liyue
 * Time 2019-02-21 9:44
 * 解密：身份(凭证)匹配器
 * 登陆时,必须将身份匹配器交给realm实现密码验证服务,否则IncorrectCredentialsException(用户名或密码不正确)异常
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    //ehcache.xml中的登录记录缓存
    private Cache<String,AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();

        AtomicInteger retryCount=passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username,retryCount);
        }

        if (retryCount.incrementAndGet()>5) {
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token,info);
        if (matches) {
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
