<%@ page import="action.UserAction" %><%--
  Created by IntelliJ IDEA.
  User: kson
  Date: 2017/9/24
  Time: 下午1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>COUNT</title>
</head>
<body>
当前在线人数:${application.onlineCount }
<BR>
登录接口调用次数：<%=UserAction.loginCount%>
<BR>
注册接口调用次数：<%=UserAction.regCount%>
</body>
</html>
