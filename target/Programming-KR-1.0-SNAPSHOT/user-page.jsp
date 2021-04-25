<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 3/31/2021
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/Main.css">
    <title>User Page</title>

</head>
<body>
<jsp:include page="WEB-INF/jsp/HeaderPart.jsp"/>

<h1>Hello, ${sessionScope.User.login}!</h1>
<a href="LogOutServlet">Выйти из профиля </a>
<div id="info">
    Login: ${sessionScope.User.login}
    Balance: ${sessionScope.User.balance}
</div>
<div id="commands" >
    <form action="AddBalanceServlet" method="post">
        Пополнить баланс:
        <input type="number" name="income">
        <button>Пополнить</button>
    </form>
    <form action="AddProductServlet" method="post" accept-charset="UTF-8">
        Добавить товар: <br>
        Название:
        <input type="text" name="name"><br>
        Информация:
        <input type="text" name="information"><br>
        <button>Добавить</button> <br>
    </form>
    <div>
        <h3>Мои товары: </h3>
            <c:if test="${sessionScope.User.products.isEmpty()}">
                У вас еще нету товаров, но вы можете их добавить
            </c:if>
        <c:forEach var="product" items="${sessionScope.User.products}">
            Название: ${product.name} <br>
            О товаре: ${product.information} id ${product.id}<br>
            <form action="ProductServlet" class="product" >
                <input type="hidden" name="product_id" value="${product.id}">
                <button>Перейти к товару.</button> <br>
            </form>
        </c:forEach>
    </div>

    <div>
        <h3>Мои лоты: </h3>
        <c:if test="${sessionScope.User.lots.isEmpty()}">
            У вас еще нету лотов, но вы можете их добавить <br>
            Что бы добавить перейдите в свой товар.
        </c:if>
        <c:forEach var="lot" items="${sessionScope.User.lots}">
            Название: ${lot.name} <br>
            О товаре: ${lot.information} id ${lot.id}<br>
            <form action="LotServlet" id="a" >
            <input type="hidden" name="lot_id" value="${lot.id}">
            <button>Перейти к лоту.</button> <br>
            </form>
        </c:forEach>
    </div>
</div>

<jsp:include page="WEB-INF/jsp/BottomPart.jsp"/>

</body>
</html>
