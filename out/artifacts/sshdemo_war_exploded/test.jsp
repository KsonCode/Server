<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>

<li><p>
    NAME:
</p>
<%= request.getParameter("name")%>

</li>

<div>
    <s:form action="login" namespace="/user" method="POST">
        <s:textfield name="mobile" label="mobile"/>
        <s:password name="password" label="password"/>
        <s:submit value="denglu"/>
    </s:form>
</div>


</body>
</html>
