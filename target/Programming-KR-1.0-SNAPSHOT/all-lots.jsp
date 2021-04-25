<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 4/25/2021
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Services.Interfaces.LotService" %>
<%@ page import="Models.Lot" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/Index.css">
    <link rel="stylesheet" href="css/Main.css">
    <title>All Lots</title>
</head>
<body>
<jsp:include page="WEB-INF/jsp/HeaderPart.jsp"/>
<div id="place_for_lots">
    <%
        LotService  lotService = (LotService) getServletConfig().getServletContext().getAttribute("lotService");
    %>

    <%
        for (Lot lot: lotService.getAllLots()) {
    %>
    <div id="lot">
        Лот <br>
        Название: <%=lot.getName()%> <br>
        Описание: <%=lot.getInformation()%> <br>
        Ставка: <%=lot.getCurrentPrice()%> <br>
        <form action="LotServlet">
            <input type="hidden" name="lot_id" value="<%=lot.getId()%>">
            <button>Подробнее</button>
        </form>
    </div>
    <%
        }
    %>

</div>
<jsp:include page="WEB-INF/jsp/BottomPart.jsp"/>
</body>
</html>
