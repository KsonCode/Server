document.writeln("<div id=\"contactwidget\">");
document.writeln("  <ul>");
document.writeln("    <li id=\"wgqq\">");
document.writeln("      <a href=\"javascript:;\">QQ</a>");
document.writeln("      <div class=\"wgbox\">");
document.writeln("        <ul>");
document.writeln("          <li><a target=\"_blank\" href=\"http://wpa.qq.com/msgrd?v=3&uin=19655910&site=qq&menu=yes\" rel=\"nofollow\" class=\"qqa\">QQ���߿ͷ�</a></li>");
document.writeln("          <li><a target=\"_blank\" href=\"https://jq.qq.com/?_wv=1027&k=4B2n357\" rel=\"nofollow\" class=\"qquna\">QQȺ</a></li>          ");
document.writeln("        </ul>");
document.writeln("      </div>");
document.writeln("      <div id=\"wgqqbotline\"></div>");
document.writeln("    </li>");
document.writeln("    <li id=\"wgwx\">");
document.writeln("      <a href=\"javascript:;\">΢��</a>");
document.writeln("      <div class=\"wgbox\"><img src=\"/static/images/qrcode200.jpg\" height=\"105\" alt=\"����΢��\" /></div>");
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