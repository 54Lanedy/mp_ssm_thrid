<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="zh-CN">
<head>
    <title>登 陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plug-in/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plug-in/bootstrap/css/demo.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plug-in/bootstrap/css/metroStyle/metroStyle.css"/>
</head>
<body>
<div class="container">
    <h2 style="text-align:center">用户登陆</h2>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
<shiro:guest>
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1" class="col-sm-3 control-label">用户</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="username" id="exampleInputEmail1" placeholder="username">
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1" class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
                    </div>
                </div>

                <div class="form-group" >
                    <label for="exampleInputEmail1" class="col-sm-3 control-label">验证码</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="captcha" id="captcha" placeholder="验证码">
                    </div>
                    <img id="_code" src="${pageContext.request.contextPath}/getCaptcha" alt="点击切换" title="点击切换"
                         onclick="refreshCaptcha();"><br>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-7">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="rememberMe"> 记住密码
                            </label>
                        </div>
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-7">
                        <button type="submit" class="btn btn-primary btn-block">登陆</button>
                    </div>
                </div>
            </form>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <label style="color: red">
                <c:out value="${message}"></c:out>
            </label>
        </div>
    </div>
</shiro:guest>
        </div>
    </div>
</div>
<shiro:user>
    <span>系统已记住您上次登录信息，点击<a href="${pageContext.request.contextPath}/main">进入系统</a> <a href="${pageContext.request.contextPath}/logout">退出</a></span>
</shiro:user>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/plug-in/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    function refreshCaptcha(){
        //随机传递一个参数，使用随机数来重新请求,没什么实际意思（主要是解决：客户端浏览器会缓存URL相同的资源）。
        $('#_code').attr({
            src: "${pageContext.request.contextPath}/getCaptcha?"+Math.random()
        })
    }
</script>
</html>
