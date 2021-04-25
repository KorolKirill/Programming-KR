<%@ page import="Services.Interfaces.LotService" %>
<%@ page import="Models.Lot" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/Index.css">
    <link rel="stylesheet" href="css/Main.css">
    <title>Система проведення аукціонів</title>
</head>
<body>

<jsp:include page="WEB-INF/jsp/HeaderPart.jsp"/>

<c:if test="${empty sessionScope.User}">

<div id="info">
        <form id="auth" action="Auth" >
            <h5>Auth:</h5>
            <label for="login">Login:</label>
            <input type="text" name="login" id="login">
            <label for="password">Password: </label>
            <input id="password" type="password" name="password">
            <button >Войти</button>
        </form>
    <c:if test="${!empty requestScope.erorMessage}">
        <div id="erorMessage">
            <h3>${requestScope.erorMessage}</h3>
        </div>
    </c:if>
    <form id="reg" method="post" action="Register">
        <h5>Register:</h5>
        <label for="login1">Login:</label> <br>
        <input type="text" name="login" id="login1">  <br>
        <label for="password1">Password: </label>  <br>
        <input id="password1" type="password" name="password">  <br>
        <button class="">Зарегаться</button>
    </form>
</div>
</c:if>

<div id="place_for_lots">
    <%
        LotService  lotService = (LotService) getServletConfig().getServletContext().getAttribute("lotService");
    %>

        <%
            int i = 0;
            for (Lot lot: lotService.getAllLots()) {
                if (i==10)
                    break;
                i++;
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