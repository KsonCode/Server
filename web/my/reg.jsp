<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/15
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0034)https://www.zhaoapi.cn/my/reg.jsp -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">

    <title>注册 - 会员管理中心 - 找数据</title>
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
    <div id="regright" class="loginright">
        <h1>欢迎注册找数据</h1>
        <form>
            <div class="formline">
                <div class="labelright"><input type="text" name="mobile" class="roundinput" id="mobile"
                                               placeholder="请输入您的手机号码"></div>
                <div class="flhint" id="mobilehint"></div>
            </div>
            <div class="formline" id="verifycodeline">
                <div class="labelright"><input type="text" name="verifycode" class="roundinput" id="verifycode"
                                               placeholder="请输入6位手机验证码">
                    <input id="btncode" type="button" value="发送验证码" class="btn" onclick="sendMessage()"></div>
                <div class="flhint" id="verifycodehint"></div>
            </div>
            <div class="formline">
                <div class="labelright"><input type="password" name="password" class="roundinput" id="password"
                                               placeholder="请输入密码 8位以上"></div>
                <div class="flhint" id="passwordhint"><span class="red"></span> 8位以上</div>
            </div>
            <div class="formline">
                <div class="labelright"><input type="text" name="email" class="roundinput" id="email"
                                               placeholder="请输入邮箱"></div>
                <div class="flhint" id="emailhint"></div>
            </div>
            <div class="formline">
                <input type="hidden" name="hashcode" id="hashcode"
                       value="AjcHYlU3VXhdbQBmBCRfaVtmU3hXZwYzU2RWel0xXTcBNVM8A2FSZAI5UDMDO1Nn">
                <input type="hidden" name="fromurl" id="fromurl" value="index.jsp">
                <input type="button" class="btn" id="regbtn" value="马上注册">
            </div>
            <div class="formline mcenter" id="regrbotline">
                已有找数据帐号？<a href="https://www.zhaoapi.cn/my/login.jsp" class="bluea">立即登录</a>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
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
