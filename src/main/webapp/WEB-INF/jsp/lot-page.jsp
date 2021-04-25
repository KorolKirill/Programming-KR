<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 4/25/2021
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lot</title>
</head>
<body>
<jsp:include page="HeaderPart.jsp"/>
    Lot №${requestScope.Lot.id} <br>
    Name: ${requestScope.Lot.name} <br>
    Owner: ${requestScope.Lot.owner} <br>
    Info: ${requestScope.Lot.information} <br>
    Current Winer: ${requestScope.Lot.winner} <br>
    Current Price: ${requestScope.Lot.currentPrice} <br>
    Min Bet Step: ${requestScope.Lot.betStep} <br>
    Date Creation: ${requestScope.Lot.dateCreation} <br> <br>

    <c:if test="${!empty sessionScope.User}">
        <c:if test="${requestScope.Lot.owner==sessionScope.User.login}">
            Hello, owner! <br>
            <form action="StopLotServlet" method="post">
                Вы можете остановить акцион, нажав на кнопку ниже. <br>
                <input type="hidden" name="lot_id" value="${requestScope.Lot.id}">
                <input type="hidden" name="winner" value="${requestScope.Lot.winner}">
                <input type="hidden" name="currentPrice" value="${requestScope.Lot.currentPrice}">

                <button>Остановить аукцион</button>
            </form>
        </c:if>
        <c:if test="${requestScope.Lot.winner==sessionScope.User.login}">
            You`re the current winner of the race <br>
        </c:if>
        <c:if test="${requestScope.Lot.owner!=sessionScope.User.login && requestScope.Lot.winner!=sessionScope.User.login}">
            Hello ${sessionScope.User.login}, you can make a bet. <br>
            Для того что бы участвовать в ауционе, вы можете поставить вашу ставку. <br>
            Your balance: ${sessionScope.User.balance}
            <form method="post" action="MakeBetServlet">
                <input type="hidden" name="lot_id" value="${requestScope.Lot.id}">
                <input type="text" name="bet_amount">
                <button>Поставить ставку</button>
            </form>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.User}">
        <h2> To perform any operation you need to auth firstly!!!</h2>
    </c:if>
    <div >
        <c:if test="${!requestScope.history.isEmpty()}">
            <h3>Bet history</h3>
        </c:if>
        <c:forEach items="${requestScope.history}" var="historyItem">
        User: ${historyItem.login}, bet amount: ${historyItem.bet}, date: ${historyItem.date} <br>
        </c:forEach>

    </div>

    <jsp:include page="BottomPart.jsp"/>

</body>
</html>
