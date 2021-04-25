<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 4/25/2021
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eror-page</title>
</head>
<body>
<jsp:include page="WEB-INF/jsp/HeaderPart.jsp"/>
<div >
    <h5>Warning!</h5> <br>

    <h3>    ${requestScope.error_message}</h3>
</div>
<jsp:include page="WEB-INF/jsp/BottomPart.jsp"/>

</body>
</html>
