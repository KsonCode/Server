<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/15
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0036)https://www.zhaoapi.cn/my/login.jsp -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">

    <title>登录 - 会员管理中心 - 找数据</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="./js/mainv2.css" rel="stylesheet" type="text/css">
    <link href="./js/my.css" rel="stylesheet" type="text/css">
    <script src="./js/hm.js"></script>
    <script type="text/javascript" src="./js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="./js/jquery.zxxbox.3.0-min.js"></script>
    <style type="text/css">#zxxBlank {
        position: absolute;
        z-index: 2000;
        left: 0;
        top: 0;
        width: 100%;
        height: 0;
        background: black;
    }

    .wrap_out {
        padding: 5px;
        background: #eee;
        box-shadow: 0 0 6px rgba(0, 0, 0, .5);
        position: absolute;
        z-index: 2000;
        left: -9999px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }

    .wrap_in {
        background: #fafafa;
        border: 1px solid #ccc;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }

    .wrap_bar {
        border-bottom: 1px solid #ddd;
        background: #f0f0f0;
        background: -moz-linear-gradient(top, #fafafa, #eee);
        background: -o-linear-gradient(top, #fafafa, #eee);
        background: -webkit-linear-gradient(top, #fafafa, #eee);
        background: linear-gradient(top, #fafafa, #eee);
    }

    .wrap_title {
        line-height: 30px;
        padding-left: 10px;
        margin: 0;
        font-weight: bold;
        font-size: 14px;
    }

    .wrap_close {
        position: relative;
    }

    .wrap_close a {
        width: 20px;
        height: 20px;
        text-align: center;
        margin-top: -25px;
        color: #34538b;
        font: bold 1em/20px Tahoma;
        text-decoration: none;
        cursor: pointer;
        position: absolute;
        right: 6px;
    }

    .wrap_close a:hover {
        text-decoration: none;
        color: #f30;
    }

    .wrap_body {
        background: white;
    }

    .wrap_remind {
        padding: 20px 20px;
        color: #333;
        min-width: 200px;
    }

    .wrap_remind p {
        margin: 10px 0 0;
    }

    .submit_btn, .cancel_btn {
        width: 100px;
    }

    .submit_btn {
    }

    .submit_btn:hover {
    }

    .cancel_btn {
        color: #666;
        background-color: #eee;
    }</style>
    <script type="text/javascript" src="./js/jquery.placeholder.js"></script>
</head>

<body class="whitebody" style="">
<div id="top" class="regtop">
    <div class="wid wid2">
        <div id="logo">
            <a href="https://www.zhaoapi.cn/">找数据</a>
        </div>
        <div id="rlogoright"><a href="https://www.zhaoapi.cn/my/login.jsp">登录</a> <span>|</span> <a
                href="https://www.zhaoapi.cn/my/reg.jsp">注册</a></div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="wid wid2">
    <div id="regbox">
        <div id="regleft">
            <a href="https://www.zhaoapi.cn/api/sms/" target="_blank"><img src="./js/adlogin.jpg"></a>
        </div>
        <div id="regright">
            <h1>欢迎登录找数据</h1>
            <s:form action="ilogin"  method="POST">
                <s:div class="formline">
                    <s:div class="labelright"><input type="text" name="mobile" class="roundinput" id="mobile"
                                                   placeholder="请输入手机号"></s:div>
                    <!--<div class="flhint" id="mobilehint"></div>-->
                </s:div>
                <s:div class="formline">
                    <s:div class="labelright"><s:password type="password" class="roundinput" name="password" id="password" placeholder="请输入密码"/></s:div>
                    <!--<div class="flhint" id="passwordhint"></div>-->
                </s:div>
                <s:div class="formline" id="autologinline">
                    <s:label class="checkbox-group" id="checkboxgroup1">
                        <s:checkbox value="true" fieldValue="1" name="defaultAddr" id="auto"/>
                        <%--<input type="checkbox" name="autologin" value="1" checked="checked">--%>
                        <span>两周内自动登录</span>
                    </s:label>
                    <s:a href="https://www.zhaoapi.cn/my/forget.jsp" class="bluea" id="forgeta">忘记密码</s:a>
                </s:div>
                <s:div class="formline">
                    <input type="hidden" name="fromurl" id="fromurl" value="">
                    <s:submit class="btn" id="regbtn" value="用户登录"/>
                </s:div>
                <s:div class="formline mcenter" id="regrbotline">
                    还没有极速帐号？<s:a href="https://www.zhaoapi.cn/my/reg.jsp" class="bluea">立即注册</s:a>
                </s:div>
            </s:form>

        </div>
        <div class="clearfix"></div>
    </div>
</div>

<div class="wid" id="foot2">
    <div class="wid2">
        <p class="clear mtop15 mcenter">&#169; </p>
    </div>
</div>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?63a21c09f2367e16d79a20fd82d6793b";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>


</body>
</html>
