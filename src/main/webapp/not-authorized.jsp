<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 3/31/2021
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>You`re not authorised</title>
    <link rel="stylesheet" href="css/Main.css">
</head>
<body>
<jsp:include page="WEB-INF/jsp/HeaderPart.jsp"/>
 <div id="">
    <h1>You`re not authorised</h1>
    <div id="commands">
        <a href="LogOutServlet">Go back </a>
    </div>
 </div>
<jsp:include page="WEB-INF/jsp/BottomPart.jsp"/>

</body>
</html>
