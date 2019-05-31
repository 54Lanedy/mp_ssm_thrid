package cn.lanedy.controller;

import cn.lanedy.utils.JsonVO;
import cn.lanedy.utils.captcha.Captcha;
import cn.lanedy.utils.captcha.SpecCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liyue
 * Time 2019-02-19 16:27
 */
@Controller
public class LoginController {

    /**
     * 登陆
     * 自定义登陆方法，即不使用shiro的formAuthenticationFilter表单验证filter
     */
//    @ResponseBody
//    @RequestMapping(value = "/login")
    public JsonVO login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "remember", required = false) String remember,
                        Model model
                       ){
        System.out.println("用户：" + username + ",密码：" + password + "  正在登陆...");
        String message;
        if (username!=null && password!=null) {
            //shiro的用户视图
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            Subject subject = SecurityUtils.getSubject();
            //默认不记住
            token.setRememberMe(false);
            //勾选了记住
            if (remember != null && remember.equals("on")) {
                token.setRememberMe(true);
            }
            try {
                subject.login(token);
                System.out.println("登陆成功");
//                return "redirect:main";
                return new JsonVO(true,"登陆成功");
            }catch (UnknownAccountException e){
                message="用户"+username+"不存在"+e.getMessage();
                e.printStackTrace();
            }catch (IncorrectCredentialsException e){
                message="用户名或密码不正确"+e.getMessage();
                e.printStackTrace();
            }catch (LockedAccountException e){
                message="用户"+username+"已被锁定"+e.getMessage();
                e.printStackTrace();
            }catch (DisabledAccountException e) {
                e.printStackTrace();
                message = "该账号已禁用，错误信息：" + e.getMessage();
            } catch (ExcessiveAttemptsException e) {
                e.printStackTrace();
                message = "该账号登录失败次数过多，错误信息：" + e.getMessage();
            } catch (Exception e){
                e.printStackTrace();
                message = "未知错误，错误信息：" + e.getMessage();
            }
        }else {
            message="请填写用户名和密码";
        }
//        model.addAttribute("message",message);
        return new JsonVO(false,message);
//        return "login";
    }
    /**
     * 登录成功，跳转到首页
     * @return
     */
    @RequestMapping("/main")
    public String index(Model model) {
        model.addAttribute("message","欢迎访问主页面...");
        return "index";
    }


    /**
     * 使用shiro的formAuthenticationFilter表单验证filter，
     * 该filter过滤loginUrl指定url页面中使用<shiro-guest></shiro-guest>标记的表单.
     * 该方法只负责监控formAuthenticationFilter表单验证之后的异常，登录失败则获取失败信息提示用户
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView doLogin(HttpServletRequest request, Model model){
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String err = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            err = "该帐号不存在！";
        } else if (IncorrectCredentialsException.class.getName().equals(
                exceptionClassName)) {
            err = "用户名/密码错误！";
        } else if (AuthenticationException.class.getName().equals(
                exceptionClassName)) {
            err = "认证失败！";
        } else if (LockedAccountException.class.getName().equals(
                exceptionClassName)) {
            err = "帐号已经锁住！";
        }else if ("captchaError".equals(exceptionClassName)) {
            err = "验证码校验失败！";
        } else if (exceptionClassName!=null){
            err = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("message", err);
        //shiro认证成功会自动跳转到上一个请求路径
        //登陆失败还到login页面
        return new ModelAndView("login");
    }

    /**
     * 获取验证码
     * 注意设置shiro的filterChainDefinitions不拦截"/getGifCode"
     */
    @RequestMapping(value = "/getCaptcha",method = RequestMethod.GET)
    public void getGifCode(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif/png 格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new SpecCaptcha(146,33,4);//png验证码
//            Captcha gifCaptcha = new GifCaptcha(146,33,4);//gif验证码
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);//true表示如果session为null则创建一个新的session
            //存入session
            session.setAttribute("validateCode",captcha.text().toLowerCase());//将大写字符转换为小写，不区分大小写
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
