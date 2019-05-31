package t1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by liyue
 * Time 2019-02-21 8:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@TransactionConfiguration(defaultRollback = false)
public class LoginTest {

    @Before
    public void loginBefore(){
        System.out.println("准备登陆...");
    }

    @Test
    public void login(){
        UsernamePasswordToken token = new UsernamePasswordToken("ly","123");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
