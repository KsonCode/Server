document.writeln("<div id=\"contactwidget\">");
document.writeln("  <ul>");
document.writeln("    <li id=\"wgqq\">");
document.writeln("      <a href=\"javascript:;\">QQ</a>");
document.writeln("      <div class=\"wgbox\">");
document.writeln("        <ul>");
document.writeln("          <li><a target=\"_blank\" href=\"http://wpa.qq.com/msgrd?v=3&uin=2020858850&site=qq&menu=yes\" rel=\"nofollow\" class=\"qqa\">QQ在线客服</a></li>");
document.writeln("          <li><a target=\"_blank\" href=\"https://jq.qq.com/?_wv=1027&k=4B2n357\" rel=\"nofollow\" class=\"qquna\">QQ群</a></li>          ");
document.writeln("        </ul>");
document.writeln("      </div>");
document.writeln("      <div id=\"wgqqbotline\"></div>");
document.writeln("    </li>");
document.writeln("    <li id=\"wgwx\">");
document.writeln("      <a href=\"javascript:;\">微信</a>");
document.writeln("      <div class=\"wgbox\"><img src=\"/static/images/qrcode200.jpg\" height=\"105\" alt=\"极速微信\" /></div>");
document.writeln("    </li>");
document.writeln("    <li id=\"wgtop\"><a href=\"javascript:;\">TOP</a></li>");
document.writeln("  </ul>");
document.writeln("</div>");

$(window).scroll(function(){
    if($(window).scrollTop()>100)
    {
        $("#wgtop").fadeIn(500);
    }
    else
    {
        $("#wgtop").fadeOut(500);
    }
});

$("#wgtop a").click(function(){
    $('body,html').animate({scrollTop:0},100);
    return false;
});

var isonwgbox = 0;
$("#wgqq>a, #wgwx>a").hover(function(){ 
    $('.wgbox').hide();    
    $(this).siblings('.wgbox').show();
}, function(){
    var _siblings = $(this).siblings('.wgbox');
    setTimeout(function(){      
        if(isonwgbox == 0) _siblings.hide();
    },500);
});

$(".wgbox").hover(function(){ 
    isonwgbox = 1;
}, function(){
    isonwgbox = 0;
    $(this).hide();
});