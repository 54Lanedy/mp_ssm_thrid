package cn.lanedy.utils;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义表单过滤器
 * Created by liyue
 * Time 2019-02-25 15:37
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 重写issueSuccessRedirect()解决successUrl路径无效问题；
     *
     * 关键：org.apache.shiro.web.util.WebUtils源码中第319行SavedRequest savedRequest = getAndClearSavedRequest(request);中的方法,只有当savedRequest为null的时候，successUrl才会是我们设置的地址。
     *
     * shiro会将跳转到登录页面前的url保存到session中，key的值叫shiroSavedRequest，而这个url通常是"/"。
     * getAndClearSavedRequest()方法中通过getSavedRequest(request)获取这个url，此时successUrl="/"。
     * 所以getSavedRequest(request)这个方法的调用，更应该是在用户登录成功后，重定向到页面时使用，此时successUrl就等于我们设置的"/main"，这时候执行重定向才是正确的。
     *
     * 重点在于WebUtils.getSavedRequest(request)的使用！
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request,response,getSuccessUrl(),null);
    }

    /**
     * 该方法在提交登陆前验证登陆请求信息，信息正确则执行executeLogin()方法验证用户和密码。
     * 重写此方法意味着验证用户密码前先验证验证码是否正确，通过则验证用户密码，否则跳转到登陆页面。
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //从session获取正确验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //取出session的验证码（正确的验证码）,生成验证码时存入session的validateCode
        String captchaFromSession = (String)session.getAttribute("validateCode");

        //取出页面输入的验证码
        //输入的验证和session中的验证进行对比
        String captchaFromJSP = httpServletRequest.getParameter("captcha");
        if (captchaFromSession!=null && captchaFromJSP!=null && !captchaFromJSP.equals(captchaFromSession)){
            //如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "captchaError");
            //拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response);
    }
}
