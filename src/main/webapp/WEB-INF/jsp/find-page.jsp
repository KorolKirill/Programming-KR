<%--
  Created by IntelliJ IDEA.
  User: Heheh
  Date: 4/25/2021
  Time: 11:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Services.Interfaces.LotService" %>
<%@ page import="Models.Lot" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Search Page</title>
</head>
<body>
<jsp:include page="HeaderPart.jsp"/>
<div>
    <form action="FindLotServlet">
        Здесь вы можете попробывать найти аукцион за один из параметров. <br>
        <input type="text" name="param">
        <select name="searchCategory">
            <option value="byOwner">By owner login</option>
            <option value="byId">By id</option>
            <option value="byLowerPrice">By price that lower than this</option>
            <option value="byGreaterPrice">>By price that greater than this</option>
            <option value="byWinner">By current winner login</option>
            <option value="byName">By Name</option>
            <option value="byInfo">By part of Info</option>
        </select>
        <button>Поиск</button>
    </form>
</div>
<div id="place_for_lots">
    <c:forEach items="${requestScope.founded}" var="lot">
        Название: ${lot.name} <br>
        О товаре: ${lot.information} id ${lot.id}<br>
        <form action="LotServlet" id="a" >
            <input type="hidden" name="lot_id" value="${lot.id}">
            <button>Перейти к лоту.</button> <br>
        </form>
    </c:forEach>
</div>
<jsp:include page="BottomPart.jsp"/>
</body>
</html>
