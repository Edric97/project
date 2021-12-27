<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/header.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${cart}" var="cart">
            <tr>
                <td>${cart.name}</td>
                <td>${cart.quantity}</td>
                <td>${cart.price}</td>
                <td>${cart.quantity * cart.price}</td>
                <td><a href="${pageContext.request.contextPath}/cart/delete?bookId=${cart.bookId}">删除</a></td>
            </tr>
        </c:forEach>



    </table>

    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${totalQuantity}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${totalPrice}</span>元</span>
        <span class="cart_span"><a href="${pageContext.request.contextPath}/cart/clearAll">清空购物车</a></span>
        <span class="cart_span"><a href="${pageContext.request.contextPath}/order/createOrder?userId=${sessionScope.userId}">去结账</a></span>
    </div>

</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>