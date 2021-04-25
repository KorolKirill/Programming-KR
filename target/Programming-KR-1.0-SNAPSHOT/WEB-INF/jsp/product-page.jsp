<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 4/25/2021
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="HeaderPart.jsp"/>
    <h2> Вы на странице продукта.</h2>
    <div id = "product">
        Name: ${requestScope.Product.name} <br>
        Owner: ${requestScope.Product.owner} <br>
        Info: ${requestScope.Product.information} <br>
    </div>
    <c:if test="${requestScope.Product.owner==sessionScope.User.login}">
        <H4> Вы владелец этого товара </H4>
        Вы можете: <br>
        <form method="post" action="CreateLotServlet">
            Добавить на акуцион: <br> <BR>
            Укажите минимальную стоимость:
            <input type="number" name="initialPrice"> <br>
            Укажите минимальный шаг повышения:
            <input type="number" name="betStep"> <br>
            <input type="hidden" name="product_id" value="${requestScope.Product.id}">
            <button>Выставить на аукцион</button> <br>
        </form>
    </c:if>
    <c:if test="${requestScope.Product.owner!=sessionScope.User.login}">
        Вы не владелец этого товара
    </c:if>
<jsp:include page="BottomPart.jsp"/>
</body>
</html>
