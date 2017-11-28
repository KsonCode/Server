<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0027)https://www.zhaoapi.cn/my/ -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">

<title>会员管理中心 - 极速数据</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="./js/admin/main.css" rel="stylesheet" type="text/css">
<link href="./js/admin/my.css" rel="stylesheet" type="text/css">
<script src="./js/admin/hm.js"></script><script type="text/javascript" src="./js/admin/jquery-2.0.3.min.js"></script>
</head>

<body style="">
<div class="wid">
  <div id="top" class="mytop">  
    <div id="logo">
      <a href="https://www.zhaoapi.cn/my/">极速数据</a>
    </div>    
    <div id="mylogoright">      
      <span id="appkey"><!--appid : nt0814092628853<br />-->appkey: 1c0dfb4bc987b871</span>
      <a href="https://www.zhaoapi.cn/" class="rightline">网站首页</a><i></i>
      <span id="membername">18612991023</span><i></i>
      <a href="https://www.zhaoapi.cn/my/login.jsp?act=exit">安全退出</a>
    </div>    
    <div class="clearfix"></div>
  </div>
</div><div class="wid">
  <div id="menu" style="height: 578px;">
    <div class="menubox">
      <h4 id="menuapi">API管理</h4>
      <ul>
	    <li><a href="https://www.zhaoapi.cn/my/api.jsp">我的API</a></li>
        <li><a href="https://www.zhaoapi.cn/my/collect.jsp">我的收藏</a></li>
        <li><a href="https://www.zhaoapi.cn/my/apply.jsp">申请API</a></li>
        <li><a href="https://www.zhaoapi.cn/my/ipwhitelist.jsp">IP白名单</a></li>
		      </ul>
    </div>
    <div class="menubox">
      <h4 id="menuaccount">账户管理</h4>
      <ul>
	    <li><a href="https://www.zhaoapi.cn/my/profile.jsp">基本资料</a></li>
        <li><a href="https://www.zhaoapi.cn/my/password.jsp">修改密码</a></li>
        <li><a href="https://www.zhaoapi.cn/my/authentication.jsp">实名认证</a></li>
        <li><a href="https://www.zhaoapi.cn/my/message.jsp">消息</a></li>
      </ul>
    </div>
    <div class="menubox">
      <h4 id="menufinance">财务管理</h4>
      <ul>
	    <li><a href="https://www.zhaoapi.cn/my/order.jsp">我的订单</a></li>
        <li><a href="https://www.zhaoapi.cn/my/coupon.jsp">优惠券</a></li>
        <li><a href="https://www.zhaoapi.cn/my/recharge.jsp">充值</a></li>
        <li><a href="https://www.zhaoapi.cn/my/invoice.jsp">开具发票</a></li>
      </ul>
    </div>
  </div>
  <div id="rcontentbox">
    <h2>我的数据 <a href="https://www.zhaoapi.cn/my/apply.jsp" class="mybtn btnred" style="margin-top: 10px;">申请数据</a></h2>
    <div id="iapilist" class="clearfix">
              <ul> 
                 
          <li>
            <div class="iapileft"><a href="https://www.zhaoapi.cn/api/recipe/" target="_blank"><img src="./js/admin/recipe.png" height="32">菜谱大全</a></div>
            <div class="iapicenter">
                            
              <div class="progressline">
                  <div class="progress">
                    <div class="progress-bar" style="width: 1.2%;">
                      <div class="indication">
                        <span class="bot"></span><span class="top"></span>12次
                      </div>
                    </div>                    
                  </div>
                  <div class="prototal">1000次</div>      
              </div>  
                                  
            </div>
            <div class="iapiright"><a href="https://www.zhaoapi.cn/my/apistat/234" class="mybtn btnwhite">统计</a><br><a href="https://www.zhaoapi.cn/my/buy/234" class="mybtn btnorange">续费</a></div>
          </li>
                    
        </ul>
            </div>
    <h2>账号信息</h2>
    <div class="rcontent accrcontent">
      <div class="myline">
        <span class="label">用户账号：</span> 18612991023 <span class="authstatus iswrong">未认证</span> <a href="https://www.zhaoapi.cn/my/authentication.jsp" class="authlink">申请认证</a>      </div>
      <div class="myline">
        <span class="label">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span> 18612991023
      </div>
      <div class="myline">
        <span class="label">邮箱地址：</span> 19655910@qq.com
      </div>      
      <div class="myline">
        <span class="label">appkey &nbsp;：</span> 1c0dfb4bc987b871
      </div>
      <div class="myline">
        <span class="label">appsecret：</span> ziQq9lwgXxdnXSrSmuV2KzXw5lW2LV7N
      </div>
      <div class="myline" style="display:none;">
        <span class="label">推广链接：</span> <span class="red">http://www.zhaoapi.cn/my/reg.jsp?u=24593</span> (复制链接分享赚极速币，邀请好友注册可得500个币！邀请越多奖越多，点击查看<a href="https://www.zhaoapi.cn/my/">邀请规则</a>)
      </div>
    </div>
    <h2>账户状态</h2>
    <div class="rcontent accrcontent" style="padding-left:0px;overflow:hidden;">
      <div class="accountline">
        账户余额：<span><i>￥</i>0.00</span> <a href="https://www.zhaoapi.cn/my/recharge_log.jsp" class="aldetail">查看明细</a> <a href="https://www.zhaoapi.cn/my/recharge.jsp" class="btna">充值</a>
      </div>
      <div class="accountline" id="acjsb" style="display:none;">
        极速币：<span><i></i>0</span> 个 <a href="https://www.zhaoapi.cn/my/" class="aldetail">查看明细</a>
      </div> 
      <div class="clearfix"></div>     
    </div>    
  </div>
</div>

<div class="clearfix"></div>
<div id="foot" class="">    
  <div class="wid">	
	<p class="clear">&#169; 2011-2015 </p>
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


</body></html>