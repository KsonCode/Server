<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>全部API - API数据大全 - 找数据</title>
    <meta name="description" content=""/>
    <meta name="keywords" content="全部API接口,API接口"/>
    <link href="./res/mainv2.css?v=20161108" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="./res/jquery-2.0.3.min.js"></script>
</head>

<body>
<div id="top">
    <div class="wid">
        <div id="logo"><a href="/">找数据</a></div>
        <div id="logocenter">
            <div id="nav">
                <ul>
                    <li><a href="/">首页</a></li>
                    <li><a href="/api/" class="on">API</a></li>
                    <li><a href="/news/">公告</a></li>
                </ul>
            </div>
        </div>
        <div id="logoright">
            <div id="searchbox">
                <form action="/search/">
                    <input id="keyword" type="text" name="keyword" placeholder="请输入API名称，如：公交" value=""/><input
                        type="submit" id="searchbtn" value="搜索"/>
                </form>
            </div>
            <div id="logindiv">
                <div id="nologinbox">
                    <a href="/my/login.jsp" class="on" rel="nofollow">登录</a><span>|</span><a href="/my/reg.jsp"
                                                                                             rel="nofollow">注册</a></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="wid" id="apilbox">
    <div id="apileft">
        <h2>API大全 (121)</h2>
        <ul>
            <li><a href="/api/1" id="iclass1">生活常用 <span>11</span></a></li>
            <li><a href="/api/4" id="iclass4">工具万能 <span>10</span></a></li>
            <li><a href="/api/2" id="iclass2">交通出行 <span>13</span></a></li>
            <li><a href="/api/3" id="iclass3">金融理财 <span>12</span></a></li>
            <li><a href="/api/12" id="iclass12">图像识别 <span>10</span></a></li>
            <li><a href="/api/9" id="iclass9">充值缴费 <span>5</span></a></li>
            <li><a href="/api/10" id="iclass10">位置服务 <span>3</span></a></li>
            <li><a href="/api/5" id="iclass5">星座算命 <span>7</span></a></li>
            <li><a href="/api/6" id="iclass6">娱乐购物 <span>7</span></a></li>
            <li><a href="/api/11" id="iclass11">新闻媒体 <span>2</span></a></li>
            <li><a href="/api/7" id="iclass7">教育亲子 <span>40</span></a></li>
            <li><a href="/api/8" id="iclass8">医药健康 <span>2</span></a></li>
        </ul>
    </div>
    <div id="apiright">
        <div id="apirtitle">
            <h1>全部API<span>121个</span></h1>
            <div id="apiltype" style="display:none;">
                <ul>
                    <li><a href="">默认排序</a></li>
                    <li>|</li>
                    <li><a href="">最新排序</a></li>
                    <li>|</li>
                    <li><a href="">使用数排序</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div id="apilistbox" class="hotdata">
            <ul>
                <li style="border-top-color:#ff7457"><a href="/api/illegal/">
                    <img src="./res/illegal.png" height="70" alt="全国交通违章查询"/>
                    <h3>全国交通违章查询</h3><span><i><del>￥</del>350</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>79671</span><span>|</span><span
                            class="collectnum"><i></i>2267</span><span>|</span><span class="usenum"><i></i>14802</span>
                    </div>
                </a></li>
                <li style="border-top-color:#4F98BD"><a href="/api/businesscardrecognition/">
                    <img src="./res/businesscardrecognition.png" height="70" alt="名片识别"/>
                    <h3>名片识别</h3><span><i><del>￥</del>45</i>/1000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>21139</span><span>|</span><span
                            class="collectnum"><i></i>1275</span><span>|</span><span class="usenum"><i></i>2211</span>
                    </div>
                </a></li>
                <li style="border-top-color:#a2e132"><a href="/api/express/">
                    <img src="./res/express.png" height="70" alt="快递查询"/>
                    <h3>快递查询</h3><span><i><del>￥</del>49</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>58898</span><span>|</span><span
                            class="collectnum"><i></i>2198</span><span>|</span><span class="usenum"><i></i>18793</span>
                    </div>
                </a></li>
                <li class="hdlilast" style="border-top-color:#7aabff"><a href="/api/proxy/">
                    <img src="./res/proxy.png" height="70" alt="代理IP"/>
                    <h3>代理IP</h3><span><i><del>￥</del>199</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>234766</span><span>|</span><span
                            class="collectnum"><i></i>1884</span><span>|</span><span class="usenum"><i></i>1980</span>
                    </div>
                </a></li>
                <li style="border-top-color:#7babff"><a href="/api/sms/">
                    <img src="./res/sms.png" height="70" alt="短信"/>
                    <h3>短信</h3><span><i><del>￥</del>37</i>/1000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>67712</span><span>|</span><span
                            class="collectnum"><i></i>1364</span><span>|</span><span class="usenum"><i></i>17100</span>
                    </div>
                </a></li>
                <li style="border-top-color:#f4d01f"><a href="/api/oil/">
                    <img src="./res/oil.png" height="70" alt="今日油价"/>
                    <h3>今日油价</h3><span><i><del>￥</del>5</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>26717</span><span>|</span><span
                            class="collectnum"><i></i>1649</span><span>|</span><span class="usenum"><i></i>2772</span>
                    </div>
                </a></li>
                <li style="border-top-color:#7babff"><a href="/api/vehiclelimit/">
                    <img src="./res/vehiclelimit.png" height="70" alt="车辆尾号限行"/>
                    <h3>车辆尾号限行</h3><span><i><del>￥</del>49</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>23788</span><span>|</span><span
                            class="collectnum"><i></i>1133</span><span>|</span><span class="usenum"><i></i>3669</span>
                    </div>
                </a></li>
                <li class="hdlilast" style="border-top-color:#93c6ec"><a href="/api/bankcardverify4/">
                    <img src="./res/bankcardverify4.png" height="70" alt="银行卡号实名认证(四元素)"/>
                    <h3>银行卡号实名认证(四元素)</h3><span><i><del>￥</del>58</i>/100次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>20141</span><span>|</span><span
                            class="collectnum"><i></i>1829</span><span>|</span><span class="usenum"><i></i>1405</span>
                    </div>
                </a></li>
                <li style="border-top-color:#ed6e58"><a href="/api/shouji/">
                    <img src="./res/shouji.png" height="70" alt="手机号码归属地"/>
                    <h3>手机号码归属地</h3><span><i><del>￥</del>9</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>26632</span><span>|</span><span
                            class="collectnum"><i></i>1211</span><span>|</span><span class="usenum"><i></i>4959</span>
                    </div>
                </a></li>
                <li style="border-top-color:#7ca2d5"><a href="/api/ip/">
                    <img src="./res/ip.png" height="70" alt="IP查询"/>
                    <h3>IP查询</h3><span><i><del>￥</del>5</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>25625</span><span>|</span><span
                            class="collectnum"><i></i>2573</span><span>|</span><span class="usenum"><i></i>5163</span>
                    </div>
                </a></li>
                <li style="border-top-color:#7babff"><a href="/api/train/">
                    <img src="./res/train.png" height="70" alt="火车查询"/>
                    <h3>火车查询</h3><span><i><del>￥</del>9</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>28050</span><span>|</span><span
                            class="collectnum"><i></i>826</span><span>|</span><span class="usenum"><i></i>6840</span>
                    </div>
                </a></li>
                <li class="hdlilast" style="border-top-color:#789CCC"><a href="/api/iqa/">
                    <div class="apitag apifree"></div>
                    <div class="apicom">小i机器人</div>
                    <img src="./res/iqa.png" height="70" alt="智能问答"/>
                    <h3>智能问答</h3><span><i class="nocalprice">免费</i></span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>31825</span><span>|</span><span
                            class="collectnum"><i></i>2080</span><span>|</span><span class="usenum"><i></i>5101</span>
                    </div>
                </a></li>
                <li style="border-top-color:#ff9575"><a href="/api/weather/">
                    <img src="./res/weather.png" height="70" alt="全国天气预报"/>
                    <h3>全国天气预报</h3><span><i><del>￥</del>5</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>43426</span><span>|</span><span
                            class="collectnum"><i></i>1915</span><span>|</span><span class="usenum"><i></i>14060</span>
                    </div>
                </a></li>
                <li style="border-top-color:#93c6ec"><a href="/api/bankcardverify/">
                    <img src="./res/bankcardverify.png" height="70" alt="银行卡号实名认证(二元素)"/>
                    <h3>银行卡号实名认证(二元素)</h3><span><i><del>￥</del>58</i>/100次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>21828</span><span>|</span><span
                            class="collectnum"><i></i>1173</span><span>|</span><span class="usenum"><i></i>1222</span>
                    </div>
                </a></li>
                <li style="border-top-color:#f4d020"><a href="/api/mobilerecharge/">
                    <img src="./res/mobilerecharge.png" height="70" alt="手机话费充值"/>
                    <h3>手机话费充值</h3><span><i class="nocalprice">按实际付费</i></span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>27639</span><span>|</span><span
                            class="collectnum"><i></i>1358</span><span>|</span><span class="usenum"><i></i>4639</span>
                    </div>
                </a></li>
                <li class="hdlilast" style="border-top-color:#93c6ec"><a href="/api/bankcardverify3/">
                    <img src="./res/bankcardverify3.png" height="70" alt="银行卡号实名认证(三元素)"/>
                    <h3>银行卡号实名认证(三元素)</h3><span><i><del>￥</del>58</i>/100次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>15032</span><span>|</span><span
                            class="collectnum"><i></i>1589</span><span>|</span><span class="usenum"><i></i>1962</span>
                    </div>
                </a></li>
                <li style="border-top-color:#97CEFF"><a href="/api/barcode2/">
                    <img src="./res/barcode2.png" height="70" alt="条码查询"/>
                    <h3>条码查询</h3><span><i><del>￥</del>98</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>20535</span><span>|</span><span
                            class="collectnum"><i></i>1338</span><span>|</span><span class="usenum"><i></i>1076</span>
                    </div>
                </a></li>
                <li style="border-top-color:#ff9575"><a href="/api/calendar/">
                    <img src="./res/calendar.png" height="70" alt="万年历"/>
                    <h3>万年历</h3><span><i><del>￥</del>49</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>31042</span><span>|</span><span
                            class="collectnum"><i></i>1279</span><span>|</span><span class="usenum"><i></i>6024</span>
                    </div>
                </a></li>
                <li style="border-top-color:#ffd800"><a href="/api/idcardverify/">
                    <img src="./res/idcardverify.png" height="70" alt="身份证实名认证"/>
                    <h3>身份证实名认证</h3><span><i><del>￥</del>65</i>/100次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>27842</span><span>|</span><span
                            class="collectnum"><i></i>2143</span><span>|</span><span class="usenum"><i></i>2456</span>
                    </div>
                </a></li>
                <li class="hdlilast" style="border-top-color:#00AAD9"><a href="/api/cell/">
                    <img src="./res/cell.png" height="70" alt="基站查询"/>
                    <h3>基站查询</h3><span><i><del>￥</del>49</i>/10000次</span>
                    <div class="apinum">
                        <span class="clicknum"><i></i>16626</span><span>|</span><span
                            class="collectnum"><i></i>2180</span><span>|</span><span class="usenum"><i></i>2104</span>
                    </div>
                </a></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="mtop10" id="pageline">
            <span class="pprev">上一页</span>
            <span class="current">1</span>


            <a href="/api/0/2">2</a>


            <a href="/api/0/3">3</a>


            <a href="/api/0/4">4</a>


            <a href="/api/0/5">5</a>


            <a href="/api/0/6">6</a>


            <a href="/api/0/7">7</a>


            <a href="/api/0/2" class="pnext">下一页</a>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<script type="text/javascript">
    $("#logininbox>a").hover(function () {
        $('#loginindown').show();
    }, function () {
        $('#loginindown').hide();
    });

    $("#loginindown").hover(function () {
        $(this).show();
        $("#logininbox>a").addClass('on');
    }, function () {
        $(this).hide();
        $("#logininbox>a").removeClass('on');
    });
</script>
<script type="text/javascript" src="./res/widget.js"></script>
<div id="foot" class="mtop15">
    <div class="wid">
        <div id="footleft"></div>
        <div id="footcenter">
            <div class="footitem">
                <h3>首页</h3>
                <ul>
                    <li><a href="/api/">所有API</a></li>
                    <li><a href="/news/">新闻动态</a></li>
                </ul>
            </div>
            <div class="footitem">
                <h3>关于</h3>
                <ul>
                    <li><a href="/about/" rel="nofollow">关于找数据</a></li>
                    <li><a href="/contact/" rel="nofollow">联系我们</a></li>
                    <li><a href="/job/" rel="nofollow">公司招聘</a></li>
                    <li><a href="/honor/" rel="nofollow">公司荣誉</a></li>
                </ul>
            </div>
        </div>
        <div id="footright">
            <div class="footitem">
                <h3>联系我们</h3>
                <div id="telbox">
                    <div class="tel">0571-56565366</div>
                    <div>邮箱：kf@zhaoapi.cn<br/>QQ ：19655910<br/>QQ群 ：(找数据③群)</div>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <p class="clear">&copy; </p>
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